package mb00.android.codehub.ui.user.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mb00.android.codehub.R;
import mb00.android.codehub.api.builder.RetrofitBuilder;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.databinding.FragmentUserReposBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.repo.adapter.RepoAdapter;
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter;
import mb00.android.codehub.ui.user.viewmodel.UserReposViewModel;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Fragment containing user repositories; launched from {@link UserFragmentPagerAdapter}
 */

public class UserReposFragment extends BaseBindingFragment<FragmentUserReposBinding, UserReposViewModel> {

    private String userLogin;
    private String authHeader;

    @Override
    protected int layout() {
        return R.layout.fragment_user_repos;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);

        if (getArguments() != null) {
            userLogin = getArguments().getString(BundleKeys.USER_NAME);
        } else {
            userLogin = preferences.getString(PreferenceKeys.USER_NAME, "");
        }
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
    }

    @Override
    public void onStart() {
        super.onStart();

        initRecyclerView();

        getBinding().userReposSwipeRefreshLayout.setOnRefreshListener(() -> {
            userReposCall(authHeader, userLogin);
            getBinding().userReposSwipeRefreshLayout.setRefreshing(false);
        });
        userReposCall(authHeader, userLogin);
    }

    private void initRecyclerView() {
        getBinding().userReposRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().userReposRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void userReposCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getUserRepos(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userRepoList -> {
                    if (userRepoList.size() > 0) {
                        RepoAdapter searchReposAdapter = new RepoAdapter(userRepoList);
                        getBinding().userReposRecyclerView.setAdapter(searchReposAdapter);
                    } else {
                        getBinding().noReposTextView.setVisibility(View.VISIBLE);
                    }
                }, error -> Timber.e(error.getMessage()));
    }

}
