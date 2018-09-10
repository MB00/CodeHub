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
import mb00.android.codehub.databinding.FragmentRepoPullRequestsBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.repo.adapter.PullRequestAdapter;
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter;
import mb00.android.codehub.ui.repo.viewmodel.RepoPullRequestsViewModel;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Fragment containing repository pull requests; launched from {@link RepoFragmentPagerAdapter}
 */

public class RepoPullRequestsFragment extends BaseBindingFragment<FragmentRepoPullRequestsBinding, RepoPullRequestsViewModel> {

    private String authHeader;
    private String userName;
    private String repoName;

    @Override
    protected int layout() {
        return R.layout.fragment_repo_pull_requests;
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

        getBinding().repoPullRequestsSwipeRefreshLayout.setOnRefreshListener(() -> {
            repoPullRequestCall(authHeader, userName, repoName);
            getBinding().repoPullRequestsSwipeRefreshLayout.setRefreshing(false);
        });
        repoPullRequestCall(authHeader, userName, repoName);
    }

    private void initRecyclerView() {
        getBinding().repoPullRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().repoPullRequestsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void repoPullRequestCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getRepoPullRequests(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoPullRequestList -> {
                    if (repoPullRequestList.size() > 0) {
                        PullRequestAdapter pullRequestAdapter = new PullRequestAdapter(repoPullRequestList, getActivity());
                        getBinding().repoPullRequestsRecyclerView.setAdapter(pullRequestAdapter);
                    } else {
                        getBinding().noPullRequestsTextView.setVisibility(View.VISIBLE);
                    }
                }, error -> Timber.e(error.getMessage()));
    }

}
