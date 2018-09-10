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
import mb00.android.codehub.databinding.FragmentUserFollowersBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.user.adapter.UserAdapter;
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter;
import mb00.android.codehub.ui.user.viewmodel.UserFollowersViewModel;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Fragment containing user followers; launched from {@link UserFragmentPagerAdapter}
 */

public class UserFollowersFragment extends BaseBindingFragment<FragmentUserFollowersBinding, UserFollowersViewModel> {

    private String userName;
    private String authHeader;

    @Override
    protected int layout() {
        return R.layout.fragment_user_followers;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);

        if (getArguments() != null) {
            userName = getArguments().getString(BundleKeys.USER_NAME);
        } else {
            userName = preferences.getString(PreferenceKeys.USER_NAME, "");
        }
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
    }

    @Override
    public void onStart() {
        super.onStart();

        initRecyclerView();

        getBinding().userFollowersSwipeRefreshLayout.setOnRefreshListener(() -> {
            userFollowersCall(authHeader, userName);
            getBinding().userFollowersSwipeRefreshLayout.setRefreshing(false);
        });
        userFollowersCall(authHeader, userName);
    }

    private void initRecyclerView() {
        getBinding().userFollowersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().userFollowersRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void userFollowersCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getUserFollowers(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userFollowersList -> {
                    if (userFollowersList.size() > 0) {
                        UserAdapter followersAdapter = new UserAdapter(userFollowersList);
                        getBinding().userFollowersRecyclerView.setAdapter(followersAdapter);
                    } else {
                        getBinding().noFollowersTextView.setVisibility(View.VISIBLE);
                    }
                }, error -> Timber.e(error.getMessage()));
    }

}
