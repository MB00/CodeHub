package mb00.android.codehub.ui.repo.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mb00.android.codehub.R;
import mb00.android.codehub.api.builder.RetrofitBuilder;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.databinding.FragmentRepoReleasesBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.repo.adapter.ReleaseAdapter;
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter;
import mb00.android.codehub.ui.repo.viewmodel.RepoReleasesViewModel;
import retrofit2.Retrofit;

/**
 * Fragment containing repository releases; launched from {@link RepoFragmentPagerAdapter}
 */

public class RepoReleasesFragment extends BaseBindingFragment<FragmentRepoReleasesBinding, RepoReleasesViewModel> {

    private String authHeader;
    private String userName;
    private String repoName;

    @Override
    protected int layout() {
        return R.layout.fragment_repo_releases;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userName = getArguments().getString(BundleKeys.USER_NAME);
        repoName = getArguments().getString(BundleKeys.REPO_NAME);
    }

    @Override
    public void onStart() {
        super.onStart();

        initRecyclerView();

        getBinding().repoReleasesSwipeRefreshLayout.setOnRefreshListener(() -> {
            repoReleaseCall(authHeader, userName, repoName);
            getBinding().repoReleasesSwipeRefreshLayout.setRefreshing(false);
        });
        repoReleaseCall(authHeader, userName, repoName);
    }

    private void initRecyclerView() {
        getBinding().repoReleaseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().repoReleaseRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void repoReleaseCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getRepoReleases(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoReleaseList -> {
                    if (repoReleaseList.size() > 0) {
                        ReleaseAdapter releaseAdapter = new ReleaseAdapter(repoReleaseList, getActivity());
                        getBinding().repoReleaseRecyclerView.setAdapter(releaseAdapter);
                    } else {
                        getBinding().noReleasesTextView.setVisibility(View.VISIBLE);
                    }
                }, Throwable::getMessage);
    }

}
