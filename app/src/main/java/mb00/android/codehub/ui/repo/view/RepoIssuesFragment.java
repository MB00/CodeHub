package mb00.android.codehub.ui.repo.view;

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
import mb00.android.codehub.api.model.Issue;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.repo.adapter.IssueAdapter;
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Fragment containing repository issues; launched from {@link RepoFragmentPagerAdapter}
 */

public class RepoIssuesFragment extends Fragment {

    //==============================================================================================
    // RepoIssuesFragment fields
    //==============================================================================================

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;
    private String repoName;

    private RecyclerView repoCodeRecyclerView;
    private IssueAdapter issueAdapter;
    private TextView noIssuesTextView;
    private SwipeRefreshLayout repoIssuesSwipeRefreshLayout;

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
        View repoCodeView = inflater.inflate(R.layout.fragment_repo_issues, container, false);

        repoCodeRecyclerView = repoCodeView.findViewById(R.id.repo_issues_recycler_view);
        noIssuesTextView = repoCodeView.findViewById(R.id.no_issues_text_view);
        repoIssuesSwipeRefreshLayout = repoCodeView.findViewById(R.id.repo_issues_swipe_refresh_layout);

        repoCodeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        repoCodeRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        repoIssuesSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                repoCodeCall(authHeader, userName, repoName);
                repoIssuesSwipeRefreshLayout.setRefreshing(false);
            }
        });
        repoCodeCall(authHeader, userName, repoName);

        return repoCodeView;
    }

    //==============================================================================================
    // RepoIssuesFragment methods
    //==============================================================================================

    private void repoCodeCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Issue>> call = service.getRepoIssues(header, user, repo);

        call.enqueue(new Callback<List<Issue>>() {
            @Override
            public void onResponse(Call<List<Issue>> call, Response<List<Issue>> response) {
                List<Issue> issueList = response.body();

                if (issueList.size() > 0) {
                    issueAdapter = new IssueAdapter(issueList);
                    repoCodeRecyclerView.setAdapter(issueAdapter);
                } else {
                    noIssuesTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Issue>> call, Throwable t) {

            }
        });
    }

}
