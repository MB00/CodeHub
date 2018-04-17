package mb00.android.codehub.ui.repo.view;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.Release;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.repo.adapter.ReleaseAdapter;
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
 * Fragment containing repository releases; launched from {@link RepoFragmentPagerAdapter}
 */

public class RepoReleasesFragment extends Fragment {

    //==============================================================================================
    // RepoReleasesFragment fields
    //==============================================================================================

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;
    private String repoName;

    private RecyclerView repoReleaseRecyclerView;
    private ReleaseAdapter releaseAdapter;
    private TextView noReleasesTextView;
    private SwipeRefreshLayout repoReleasesSwipeRefreshLayout;

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
        View repoReleaseView = inflater.inflate(R.layout.fragment_repo_releases, container, false);

        repoReleaseRecyclerView = (RecyclerView) repoReleaseView.findViewById(R.id.repo_release_recycler_view);
        noReleasesTextView = (TextView) repoReleaseView.findViewById(R.id.no_releases_text_view);
        repoReleasesSwipeRefreshLayout = (SwipeRefreshLayout) repoReleaseView.findViewById(R.id.repo_releases_swipe_refresh_layout);

        repoReleaseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        repoReleaseRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        repoReleasesSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                repoReleaseCall(authHeader, userName, repoName);
                repoReleasesSwipeRefreshLayout.setRefreshing(false);
            }
        });
        repoReleaseCall(authHeader, userName, repoName);

        return repoReleaseView;
    }

    //==============================================================================================
    // RepoReleasesFragment methods
    //==============================================================================================

    private void repoReleaseCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Release>> call = service.getRepoReleases(header, user, repo);

        call.enqueue(new Callback<List<Release>>() {
            @Override
            public void onResponse(Call<List<Release>> call, Response<List<Release>> response) {
                List<Release> repoReleaseList = response.body();

                if (repoReleaseList.size() > 0) {
                    releaseAdapter = new ReleaseAdapter(repoReleaseList, getActivity());
                    repoReleaseRecyclerView.setAdapter(releaseAdapter);
                } else {
                    noReleasesTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Release>> call, Throwable t) {

            }
        });
    }

}