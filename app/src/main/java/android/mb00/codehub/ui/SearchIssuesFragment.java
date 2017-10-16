package android.mb00.codehub.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.mb00.codehub.R;
import android.mb00.codehub.api.model.Issue;
import android.mb00.codehub.api.model.IssueResult;
import android.mb00.codehub.api.service.GitHubService;
import android.mb00.codehub.data.BundleKeys;
import android.mb00.codehub.api.RetrofitBuilder;
import android.mb00.codehub.data.PreferenceKeys;
import android.mb00.codehub.ui.adapter.IssueAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


public class SearchIssuesFragment extends Fragment {

    private SharedPreferences preferences;
    private String authHeader;
    private String issue;

    private RecyclerView searchIssuesRecyclerView;
    private IssueAdapter searchIssuesAdapter;
    private TextView noIssueResultsTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View searchIssuesView = inflater.inflate(R.layout.fragment_search_issues, container, false);
        searchIssuesRecyclerView = (RecyclerView) searchIssuesView.findViewById(R.id.search_issues_recycler_view);
        searchIssuesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchIssuesRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        noIssueResultsTextView = (TextView) searchIssuesView.findViewById(R.id.no_issue_results_text_view);

        issue = getArguments().getString(BundleKeys.SEARCH_QUERY_KEY);
        issueCall(authHeader, issue);

        return searchIssuesView;
    }

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
