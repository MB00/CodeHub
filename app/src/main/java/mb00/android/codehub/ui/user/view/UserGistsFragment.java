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
import mb00.android.codehub.databinding.FragmentUserGistsBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.gist.adapter.GistAdapter;
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter;
import mb00.android.codehub.ui.user.viewmodel.UserGistsViewModel;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Fragment containing user gists; launched from {@link UserFragmentPagerAdapter}
 */

public class UserGistsFragment extends BaseBindingFragment<FragmentUserGistsBinding, UserGistsViewModel> {

    private String authHeader;
    private String userName;

    @Override
    protected int layout() {
        return R.layout.fragment_user_gists;
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

        getBinding().userGistsSwipeRefreshLayout.setOnRefreshListener(() -> {
            userGistsCall(authHeader, userName);
            getBinding().userGistsSwipeRefreshLayout.setRefreshing(false);
        });
        userGistsCall(authHeader, userName);
    }

    private void initRecyclerView() {
        getBinding().userGistsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().userGistsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void userGistsCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getUserGists(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userGistList -> {
                    if (userGistList.size() > 0) {
                        GistAdapter searchGistsAdapter = new GistAdapter(userGistList);
                        getBinding().userGistsRecyclerView.setAdapter(searchGistsAdapter);
                    } else {
                        getBinding().noGistsTextView.setVisibility(View.VISIBLE);
                    }
                }, error -> Timber.e(error.getMessage()));
    }

}
