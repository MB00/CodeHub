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
 * Fragment containing user repositories; launched from {@link UserFragmentPagerAdapter}
 */

public class UserReposFragment extends Fragment {

    //==============================================================================================
    // UserReposFragment fields
    //==============================================================================================

    private SharedPreferences preferences;
    private String userLogin;
    private String authHeader;

    private RecyclerView userReposRecyclerView;
    private RepoAdapter searchReposAdapter;
    private TextView noReposTextView;
    private SwipeRefreshLayout userReposSwipeRefreshLayout;

    //==============================================================================================
    // Fragment / lifecycle methods
    //==============================================================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);

        if (getArguments() != null) {
            userLogin = getArguments().getString(BundleKeys.USER_NAME);
        } else {
            userLogin = preferences.getString(PreferenceKeys.USER_NAME, "");
        }
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View userRepoView = inflater.inflate(R.layout.fragment_user_repos, container, false);

        userReposRecyclerView = userRepoView.findViewById(R.id.user_repos_recycler_view);
        noReposTextView = userRepoView.findViewById(R.id.no_repos_text_view);
        userReposSwipeRefreshLayout = userRepoView.findViewById(R.id.user_repos_swipe_refresh_layout);

        userReposRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        userReposRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        userReposSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userReposCall(authHeader, userLogin);
                userReposSwipeRefreshLayout.setRefreshing(false);
            }
        });
        userReposCall(authHeader, userLogin);

        return userRepoView;
    }

    //==============================================================================================
    // UserReposFragment methods
    //==============================================================================================

    private void userReposCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> call = service.getUserRepos(header, user);

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> userRepoList = response.body();

                if (userRepoList.size() > 0) {
                    searchReposAdapter = new RepoAdapter(userRepoList);
                    userReposRecyclerView.setAdapter(searchReposAdapter);
                } else {
                    noReposTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }

}
