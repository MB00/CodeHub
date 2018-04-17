package mb00.android.codehub.ui.user.view;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.User;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.user.adapter.UserAdapter;
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Fragment containing user followers; launched from {@link UserFragmentPagerAdapter}
 */

public class UserFollowersFragment extends Fragment {

    //==============================================================================================
    // UserFollowersFragment fields
    //==============================================================================================

    private SharedPreferences preferences;
    private String userName;
    private String authHeader;

    private RecyclerView userFollowersRecyclerView;
    private UserAdapter followersAdapter;
    private TextView noFollowersTextView;
    private SwipeRefreshLayout userFollowersSwipeRefreshLayout;

    //==============================================================================================
    // Fragment / lifecycle methods
    //==============================================================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);

        if (getArguments() != null) {
            userName = getArguments().getString(BundleKeys.USER_NAME);
        } else {
            userName = preferences.getString(PreferenceKeys.USER_NAME, "");
        }
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View userFollowerView = inflater.inflate(R.layout.fragment_user_followers, container, false);

        userFollowersRecyclerView = (RecyclerView) userFollowerView.findViewById(R.id.user_followers_recycler_view);
        noFollowersTextView = (TextView) userFollowerView.findViewById(R.id.no_followers_text_view);
        userFollowersSwipeRefreshLayout = (SwipeRefreshLayout) userFollowerView.findViewById(R.id.user_followers_swipe_refresh_layout);

        userFollowersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        userFollowersRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        userFollowersSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userFollowersCall(authHeader, userName);
                userFollowersSwipeRefreshLayout.setRefreshing(false);
            }
        });
        userFollowersCall(authHeader, userName);

        return userFollowerView;
    }

    //==============================================================================================
    // UserFollowersFragment methods
    //==============================================================================================

    private void userFollowersCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<User>> call = service.getUserFollowers(header, user);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userFollowersList = response.body();

                if (userFollowersList.size() > 0) {
                    followersAdapter = new UserAdapter(userFollowersList);
                    userFollowersRecyclerView.setAdapter(followersAdapter);
                } else {
                    noFollowersTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

}
