package android.mb00.codehub.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.mb00.codehub.R;
import android.mb00.codehub.api.model.Repo;
import android.mb00.codehub.api.model.RepoResult;
import android.mb00.codehub.api.service.GitHubService;
import android.mb00.codehub.data.BundleKeys;
import android.mb00.codehub.api.RetrofitBuilder;
import android.mb00.codehub.data.PreferenceKeys;
import android.mb00.codehub.ui.adapter.RepoAdapter;
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


public class SearchReposFragment extends Fragment {

    private SharedPreferences preferences;
    private String authHeader;
    private String repo;

    private RecyclerView searchReposRecyclerView;
    private RepoAdapter searchReposAdapter;
    private TextView noRepoResultsTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View searchReposView = inflater.inflate(R.layout.fragment_search_repos, container, false);
        searchReposRecyclerView = (RecyclerView) searchReposView.findViewById(R.id.search_repos_recycler_view);
        searchReposRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchReposRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        noRepoResultsTextView = (TextView) searchReposView.findViewById(R.id.no_repo_results_text_view);

        repo = getArguments().getString(BundleKeys.SEARCH_QUERY_KEY);
        repoCall(authHeader, repo);


        return searchReposView;
    }

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
