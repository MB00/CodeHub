package mb00.android.codehub.ui.search.view;

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
import mb00.android.codehub.api.model.RepoResult;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.databinding.FragmentSearchReposBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.repo.adapter.RepoAdapter;
import mb00.android.codehub.ui.search.adapter.SearchFragmentPagerAdapter;
import mb00.android.codehub.ui.search.viewmodel.SearchReposViewModel;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Fragment containing repository search results; launched from {@link SearchFragmentPagerAdapter}
 */

public class SearchReposFragment extends BaseBindingFragment<FragmentSearchReposBinding, SearchReposViewModel> {

    private String authHeader;
    private String repo;

    @Override
    protected int layout() {
        return R.layout.fragment_search_repos;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPreferences();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
        initSwipeRefreshListener();
    }

    @Override
    public void onResume() {
        super.onResume();

        repo = getArguments().getString(BundleKeys.SEARCH_QUERY_KEY);
        repoCall(authHeader, repo);
    }

    private void initPreferences() {
        SharedPreferences preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
    }

    private void initRecyclerView() {
        getBinding().searchReposRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().searchReposRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void initSwipeRefreshListener() {
        getBinding().searchReposSwipeRefreshLayout.setOnRefreshListener(() -> {
            repoCall(authHeader, repo);
            getBinding().searchReposSwipeRefreshLayout.setRefreshing(false);
        });
    }

    private void repoCall(String header, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.repoSearch(header, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(RepoResult::getItems)
                .subscribe(repoList -> {
                    if (repoList.size() > 0) {
                        RepoAdapter searchReposAdapter = new RepoAdapter(repoList);
                        getBinding().searchReposRecyclerView.setAdapter(searchReposAdapter);
                    } else {
                        getBinding().noRepoResultsTextView.setVisibility(View.VISIBLE);
                    }
                }, error -> {
                    getBinding().noRepoResultsTextView.setVisibility(View.VISIBLE);
                    Timber.e(error.getMessage());
                });
    }

}
