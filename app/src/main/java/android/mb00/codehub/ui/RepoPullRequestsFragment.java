package android.mb00.codehub.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.mb00.codehub.R;
import android.mb00.codehub.api.model.PullRequest;
import android.mb00.codehub.api.service.GitHubService;
import android.mb00.codehub.data.BundleKeys;
import android.mb00.codehub.api.RetrofitBuilder;
import android.mb00.codehub.data.PreferenceKeys;
import android.mb00.codehub.ui.adapter.PullRequestAdapter;
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


public class RepoPullRequestsFragment extends Fragment {

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;
    private String repoName;

    private RecyclerView repoPullRequestRecyclerView;
    private PullRequestAdapter pullRequestAdapter;
    private TextView noPullRequestsTextView;

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
        View repoCodeView = inflater.inflate(R.layout.fragment_repo_pull_requests, container, false);

        repoPullRequestRecyclerView = (RecyclerView) repoCodeView.findViewById(R.id.repo_pull_requests_recycler_view);
        noPullRequestsTextView = (TextView) repoCodeView.findViewById(R.id.no_pull_requests_text_view);

        repoPullRequestRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        repoPullRequestRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        repoPullRequestCall(authHeader, userName, repoName);

        return repoCodeView;
    }

    private void repoPullRequestCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<PullRequest>> call = service.getRepoPullRequests(header, user, repo);

        call.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                List<PullRequest> repoPullRequestList = response.body();

                if (repoPullRequestList.size() > 0) {
                    pullRequestAdapter = new PullRequestAdapter(repoPullRequestList, getActivity());
                    repoPullRequestRecyclerView.setAdapter(pullRequestAdapter);
                } else {
                    noPullRequestsTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {

            }
        });
    }

}
