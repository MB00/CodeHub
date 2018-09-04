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
import mb00.android.codehub.databinding.FragmentHomeFollowingBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.home.adapter.HomeFragmentPagerAdapter;
import mb00.android.codehub.ui.home.viewmodel.HomeViewModel;
import mb00.android.codehub.ui.user.adapter.UserAdapter;
import retrofit2.Retrofit;

/**
 * Fragment containing users following; launched from {@link HomeFragmentPagerAdapter}
 */

public class HomeFollowingFragment extends BaseBindingFragment<FragmentHomeFollowingBinding, HomeViewModel> {

    private String userName;
    private String authHeader;

    @Override
    protected int layout() {
        return R.layout.fragment_home_following;
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

        getBinding().userFollowingSwipeRefreshLayout.setOnRefreshListener(() -> {
            userFollowingCall(authHeader, userName);
            getBinding().userFollowingSwipeRefreshLayout.setRefreshing(false);
        });
        userFollowingCall(authHeader, userName);
    }

    private void initRecyclerView() {
        getBinding().userFollowingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().userFollowingRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }


    private void userFollowingCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getUserFollowing(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userFollowingList -> {
                    if (userFollowingList.size() > 0) {
                        UserAdapter followingAdapter = new UserAdapter(userFollowingList);
                        getBinding().userFollowingRecyclerView.setAdapter(followingAdapter);
                    } else {
                        getBinding().noneFollowingTextView.setVisibility(View.VISIBLE);
                    }
                });
    }

}