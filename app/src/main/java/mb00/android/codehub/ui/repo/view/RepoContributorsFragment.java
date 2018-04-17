package mb00.android.codehub.ui.repo.view;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.Contributor;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.repo.adapter.ContributorAdapter;
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter;

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
 * Fragment containing repository contributors; launched from {@link RepoFragmentPagerAdapter}
 */

public class RepoContributorsFragment extends Fragment {

    //==============================================================================================
    // RepoContributorsFragment fields
    //==============================================================================================

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;
    private String repoName;

    private RecyclerView repoContributorRecyclerView;
    private ContributorAdapter contributorAdapter;
    private TextView noContributorsTextView;
    private SwipeRefreshLayout repoContributorsSwipeRefreshLayout;

    //==============================================================================================
    // Fragment / lifecycle methods
    //==============================================================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userName = getArguments().getString(BundleKeys.USER_NAME);
        repoName = getArguments().getString(BundleKeys.REPO_NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View repoContributorView = inflater.inflate(R.layout.fragment_repo_contributors, container, false);

        repoContributorRecyclerView = (RecyclerView) repoContributorView.findViewById(R.id.repo_contributor_recycler_view);
        noContributorsTextView = (TextView) repoContributorView.findViewById(R.id.no_contributors_text_view);
        repoContributorsSwipeRefreshLayout = (SwipeRefreshLayout) repoContributorView.findViewById(R.id.repo_contributors_swipe_refresh_layout);

        repoContributorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        repoContributorRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        repoContributorsSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                repoContributorCall(authHeader, userName, repoName);
                repoContributorsSwipeRefreshLayout.setRefreshing(false);
            }
        });
        repoContributorCall(authHeader, userName, repoName);

        return repoContributorView;
    }

    //==============================================================================================
    // RepoContributorsFragment methods
    //==============================================================================================

    private void repoContributorCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Contributor>> call = service.getRepoContributors(header, user, repo);

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                List<Contributor> repoContributorsList = response.body();

                if (repoContributorsList.size() > 0) {
                    contributorAdapter = new ContributorAdapter(repoContributorsList);
                    repoContributorRecyclerView.setAdapter(contributorAdapter);
                } else {
                    noContributorsTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {

            }
        });
    }

}
