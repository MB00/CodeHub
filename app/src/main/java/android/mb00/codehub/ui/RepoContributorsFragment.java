package android.mb00.codehub.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.mb00.codehub.R;
import android.mb00.codehub.api.model.Contributor;
import android.mb00.codehub.api.service.GitHubService;
import android.mb00.codehub.data.BundleKeys;
import android.mb00.codehub.api.RetrofitBuilder;
import android.mb00.codehub.data.PreferenceKeys;
import android.mb00.codehub.ui.adapter.ContributorAdapter;
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


public class RepoContributorsFragment extends Fragment {

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;
    private String repoName;

    private RecyclerView repoContributorRecyclerView;
    private ContributorAdapter contributorAdapter;
    private TextView noContributorsTextView;

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

        repoContributorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        repoContributorRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        repoContributorCall(authHeader, userName, repoName);

        return repoContributorView;
    }

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
