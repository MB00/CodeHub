package mb00.android.codehub.ui.search.view;

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
import mb00.android.codehub.api.model.RepoResult;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.repo.adapter.RepoAdapter;
import mb00.android.codehub.ui.search.adapter.SearchFragmentPagerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Fragment containing repository search results; launched from {@link SearchFragmentPagerAdapter}
 */

public class SearchReposFragment extends Fragment {

    //==============================================================================================
    // SearchReposFragment fields
    //==============================================================================================

    private SharedPreferences preferences;
    private String authHeader;
    private String repo;

    private RecyclerView searchReposRecyclerView;
    private RepoAdapter searchReposAdapter;
    private TextView noRepoResultsTextView;
    private SwipeRefreshLayout searchReposSwipeRefreshLayout;

    //==============================================================================================
    // Fragment / lifecycle methods
    //==============================================================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View searchReposView = inflater.inflate(R.layout.fragment_search_repos, container, false);

        repo = getArguments().getString(BundleKeys.SEARCH_QUERY_KEY);
        searchReposRecyclerView = searchReposView.findViewById(R.id.search_repos_recycler_view);
        noRepoResultsTextView = searchReposView.findViewById(R.id.no_repo_results_text_view);
        searchReposSwipeRefreshLayout = searchReposView.findViewById(R.id.search_repos_swipe_refresh_layout);

        searchReposRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchReposRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        searchReposSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                repoCall(authHeader, repo);
                searchReposSwipeRefreshLayout.setRefreshing(false);
            }
        });
        repoCall(authHeader, repo);

        return searchReposView;
    }

    //==============================================================================================
    // SearchReposFragment methods
    //==============================================================================================

    private void repoCall(String header, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<RepoResult> call = service.repoSearch(header, repo);

        call.enqueue(new Callback<RepoResult>() {
            @Override
            public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {
                if (response.isSuccessful()) {
                    List<Repo> repoList = response.body().getItems();

                    if (repoList.size() > 0) {
                        searchReposAdapter = new RepoAdapter(repoList);
                        searchReposRecyclerView.setAdapter(searchReposAdapter);
                    } else {
                        noRepoResultsTextView.setVisibility(View.VISIBLE);
                    }
                } else {
                    noRepoResultsTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<RepoResult> call, Throwable t) {

            }
        });
    }

}
