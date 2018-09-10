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
import mb00.android.codehub.databinding.FragmentRepoIssuesBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.repo.adapter.IssueAdapter;
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter;
import mb00.android.codehub.ui.repo.viewmodel.RepoIssuesViewModel;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Fragment containing repository issues; launched from {@link RepoFragmentPagerAdapter}
 */

public class RepoIssuesFragment extends BaseBindingFragment<FragmentRepoIssuesBinding, RepoIssuesViewModel> {

    private String authHeader;
    private String userName;
    private String repoName;

    @Override
    protected int layout() {
        return R.layout.fragment_repo_issues;
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

        getBinding().repoIssuesSwipeRefreshLayout.setOnRefreshListener(() -> {
            repoCodeCall(authHeader, userName, repoName);
            getBinding().repoIssuesSwipeRefreshLayout.setRefreshing(false);
        });
        repoCodeCall(authHeader, userName, repoName);
    }

    private void initRecyclerView() {
        getBinding().repoIssuesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().repoIssuesRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void repoCodeCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getRepoIssues(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoIssueList -> {
                    if (repoIssueList.size() > 0) {
                        IssueAdapter issueAdapter = new IssueAdapter(repoIssueList);
                        getBinding().repoIssuesRecyclerView.setAdapter(issueAdapter);
                    } else {
                        getBinding().noIssuesTextView.setVisibility(View.VISIBLE);
                    }
                }, error -> Timber.e(error.getMessage()));
    }

}
