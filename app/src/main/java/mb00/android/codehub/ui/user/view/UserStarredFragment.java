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
import mb00.android.codehub.databinding.FragmentUserStarredBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.repo.adapter.RepoAdapter;
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter;
import mb00.android.codehub.ui.user.viewmodel.UserStarredViewModel;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Fragment containing user-starred repositories; launched from {@link UserFragmentPagerAdapter}
 */

public class UserStarredFragment extends BaseBindingFragment<FragmentUserStarredBinding, UserStarredViewModel> {

    private String authHeader;
    private String userName;

    @Override
    protected int layout() {
        return R.layout.fragment_user_starred;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userName = getArguments().getString(BundleKeys.USER_NAME);
    }

    @Override
    public void onStart() {
        super.onStart();

        initRecyclerView();

        getBinding().userStarredSwipeRefreshLayout.setOnRefreshListener(() -> {
            userStarredCall(authHeader, userName);
            getBinding().userStarredSwipeRefreshLayout.setRefreshing(false);
        });
        userStarredCall(authHeader, userName);
    }

    private void initRecyclerView() {
        getBinding().userStarredRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().userStarredRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void userStarredCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getUserStarred(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userStarredList -> {
                    if (userStarredList.size() > 0) {
                        RepoAdapter starredAdapter = new RepoAdapter(userStarredList);
                        getBinding().userStarredRecyclerView.setAdapter(starredAdapter);
                    } else {
                        getBinding().noneStarredTextView.setVisibility(View.VISIBLE);
                    }
                }, error -> Timber.e(error.getMessage()));
    }

}
