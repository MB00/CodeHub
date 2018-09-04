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
import mb00.android.codehub.databinding.FragmentRepoContributorsBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.repo.adapter.ContributorAdapter;
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter;
import mb00.android.codehub.ui.repo.viewmodel.RepoContributorsViewModel;
import retrofit2.Retrofit;

/**
 * Fragment containing repository contributors; launched from {@link RepoFragmentPagerAdapter}
 */

public class RepoContributorsFragment extends BaseBindingFragment<FragmentRepoContributorsBinding, RepoContributorsViewModel> {

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;
    private String repoName;

    private ContributorAdapter contributorAdapter;

    @Override
    protected int layout() {
        return R.layout.fragment_repo_contributors;
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

        getBinding().repoContributorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().repoContributorRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        getBinding().repoContributorsSwipeRefreshLayout.setOnRefreshListener(() -> {
            repoContributorCall(authHeader, userName, repoName);
            getBinding().repoContributorsSwipeRefreshLayout.setRefreshing(false);
        });
        repoContributorCall(authHeader, userName, repoName);
    }

    private void repoContributorCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getRepoContributors(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoContributorsList -> {
                    if (repoContributorsList.size() > 0) {
                        contributorAdapter = new ContributorAdapter(repoContributorsList);
                        getBinding().repoContributorRecyclerView.setAdapter(contributorAdapter);
                    } else {
                        getBinding().noContributorsTextView.setVisibility(View.VISIBLE);
                    }
                }, Throwable::getMessage);
    }

}
