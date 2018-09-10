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
import mb00.android.codehub.databinding.FragmentRepoCommitsBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.repo.adapter.CommitAdapter;
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter;
import mb00.android.codehub.ui.repo.viewmodel.RepoCommitsViewModel;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Fragment containing repository commits; launched from {@link RepoFragmentPagerAdapter}
 */

public class RepoCommitsFragment extends BaseBindingFragment<FragmentRepoCommitsBinding, RepoCommitsViewModel> {


    private SharedPreferences preferences;
    private String authHeader;
    private String userName;
    private String repoName;

    private CommitAdapter commitAdapter;


    @Override
    protected int layout() {
        return R.layout.fragment_repo_commits;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userName = getArguments().getString(BundleKeys.USER_NAME);
        repoName = getArguments().getString(BundleKeys.REPO_NAME);
    }

    @Override
    public void onStart() {
        super.onStart();

        initRecyclerView();

        getBinding().repoCommitsSwipeRefreshLayout.setOnRefreshListener(() -> {
            repoCommitCall(authHeader, userName, repoName);
            getBinding().repoCommitsSwipeRefreshLayout.setRefreshing(false);
        });

        repoCommitCall(authHeader, userName, repoName);
    }

    private void initRecyclerView() {
        getBinding().repoCommitRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().repoCommitRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void repoCommitCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getRepoCommits(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoCommitsList -> {
                    if (repoCommitsList.size() > 0) {
                        commitAdapter = new CommitAdapter(repoCommitsList, getActivity());
                        getBinding().repoCommitRecyclerView.setAdapter(commitAdapter);
                    } else {
                        getBinding().noCommitsTextView.setVisibility(View.VISIBLE);
                    }
                }, error -> Timber.e(error.getMessage()));
    }

}
