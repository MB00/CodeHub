package mb00.android.codehub.ui;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.Commit;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.adapter.CommitAdapter;

import android.content.Context;
import android.content.SharedPreferences;
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


public class RepoCommitsFragment extends Fragment {

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;
    private String repoName;

    private RecyclerView repoCommitRecyclerView;
    private CommitAdapter commitAdapter;
    private TextView noCommitsTextView;

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
        View repoCommitView = inflater.inflate(R.layout.fragment_repo_commits, container, false);

        repoCommitRecyclerView = (RecyclerView) repoCommitView.findViewById(R.id.repo_commit_recycler_view);
        noCommitsTextView = (TextView) repoCommitView.findViewById(R.id.no_commits_text_view);

        repoCommitRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        repoCommitRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        repoCommitCall(authHeader, userName, repoName);

        return repoCommitView;
    }

    private void repoCommitCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Commit>> call = service.getRepoCommits(header, user, repo);

        call.enqueue(new Callback<List<Commit>>() {
            @Override
            public void onResponse(Call<List<Commit>> call, Response<List<Commit>> response) {
                List<Commit> repoCommitsList = response.body();

                if (repoCommitsList.size() > 0) {
                    commitAdapter = new CommitAdapter(repoCommitsList, getActivity());
                    repoCommitRecyclerView.setAdapter(commitAdapter);
                } else {
                    noCommitsTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Commit>> call, Throwable t) {

            }
        });
    }

}
