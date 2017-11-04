package mb00.android.codehub.ui;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.Issue;
import mb00.android.codehub.api.model.IssueResult;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.adapter.IssueAdapter;
import mb00.android.codehub.ui.adapter.SearchFragmentPagerAdapter;

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
 * Fragment containing issue search results; launched from {@link SearchFragmentPagerAdapter}
 */

public class SearchIssuesFragment extends Fragment {

    //==============================================================================================
    // SearchIssuesFragment fields
    //==============================================================================================

    private SharedPreferences preferences;
    private String authHeader;
    private String issue;

    private RecyclerView searchIssuesRecyclerView;
    private IssueAdapter searchIssuesAdapter;
    private TextView noIssueResultsTextView;
    private SwipeRefreshLayout searchIssuesSwipeRefreshLayout;

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
        View searchIssuesView = inflater.inflate(R.layout.fragment_search_issues, container, false);

        issue = getArguments().getString(BundleKeys.SEARCH_QUERY_KEY);
        searchIssuesRecyclerView = (RecyclerView) searchIssuesView.findViewById(R.id.search_issues_recycler_view);
        noIssueResultsTextView = (TextView) searchIssuesView.findViewById(R.id.no_issue_results_text_view);
        searchIssuesSwipeRefreshLayout = (SwipeRefreshLayout) searchIssuesView.findViewById(R.id.search_issues_swipe_refresh_layout);

        searchIssuesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchIssuesRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        searchIssuesSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                issueCall(authHeader, issue);
                searchIssuesSwipeRefreshLayout.setRefreshing(false);
            }
        });
        issueCall(authHeader, issue);

        return searchIssuesView;
    }

    //==============================================================================================
    // SearchIssuesFragment methods
    //==============================================================================================

    private void issueCall(String header, String issue) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<IssueResult> call = service.issueSearch(header, issue);

        call.enqueue(new Callback<IssueResult>() {
            @Override
            public void onResponse(Call<IssueResult> call, Response<IssueResult> response) {
                if (response.isSuccessful()) {
                    List<Issue> issueList = response.body().getItems();

                    if (issueList.size() > 0) {
                        searchIssuesAdapter = new IssueAdapter(issueList);
                        searchIssuesRecyclerView.setAdapter(searchIssuesAdapter);
                    } else {
                        noIssueResultsTextView.setVisibility(View.VISIBLE);
                    }
                } else {
                    noIssueResultsTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<IssueResult> call, Throwable t) {

            }
        });
    }

}
