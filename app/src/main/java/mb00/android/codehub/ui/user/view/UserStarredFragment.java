package mb00.android.codehub.ui.user.view;

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

import mb00.android.codehub.R;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.api.model.Repo;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.repo.adapter.RepoAdapter;
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Fragment containing user-starred repositories; launched from {@link UserFragmentPagerAdapter}
 */

public class UserStarredFragment extends Fragment {

    //==============================================================================================
    // UserStarredFragment fields
    //==============================================================================================

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;

    private RecyclerView userStarredRecyclerView;
    private RepoAdapter starredAdapter;
    private TextView noneStarredTextView;
    private SwipeRefreshLayout userStarredSwipeRefreshLayout;

    //==============================================================================================
    // Fragment / lifecycle methods
    //==============================================================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userName = getArguments().getString(BundleKeys.USER_NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View userFollowerView = inflater.inflate(R.layout.fragment_user_starred, container, false);

        userStarredRecyclerView = userFollowerView.findViewById(R.id.user_starred_recycler_view);
        noneStarredTextView = userFollowerView.findViewById(R.id.none_starred_text_view);
        userStarredSwipeRefreshLayout = userFollowerView.findViewById(R.id.user_starred_swipe_refresh_layout);

        userStarredRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        userStarredRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        userStarredSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userStarredCall(authHeader, userName);
                userStarredSwipeRefreshLayout.setRefreshing(false);
            }
        });
        userStarredCall(authHeader, userName);

        return userFollowerView;
    }

    //==============================================================================================
    // UserStarredFragment methods
    //==============================================================================================

    private void userStarredCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> call = service.getUserStarred(header, user);

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> userStarredList = response.body();

                if (userStarredList.size() > 0) {
                    starredAdapter = new RepoAdapter(userStarredList);
                    userStarredRecyclerView.setAdapter(starredAdapter);
                } else {
                    noneStarredTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }

}
