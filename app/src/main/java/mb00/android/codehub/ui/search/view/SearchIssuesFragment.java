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
import mb00.android.codehub.api.model.IssueResult;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.databinding.FragmentSearchIssuesBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.repo.adapter.IssueAdapter;
import mb00.android.codehub.ui.search.adapter.SearchFragmentPagerAdapter;
import mb00.android.codehub.ui.search.viewmodel.SearchIssuesViewModel;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Fragment containing issue search results; launched from {@link SearchFragmentPagerAdapter}
 */

public class SearchIssuesFragment extends BaseBindingFragment<FragmentSearchIssuesBinding, SearchIssuesViewModel> {

    private String authHeader;
    private String issue;

    @Override
    protected int layout() {
        return R.layout.fragment_search_issues;
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

        issue = getArguments().getString(BundleKeys.SEARCH_QUERY_KEY);
        issueCall(authHeader, issue);
    }

    private void initPreferences() {
        SharedPreferences preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
    }

    private void initRecyclerView() {
        getBinding().searchIssuesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().searchIssuesRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void initSwipeRefreshListener() {
        getBinding().searchIssuesSwipeRefreshLayout.setOnRefreshListener(() -> {
            issueCall(authHeader, issue);
            getBinding().searchIssuesSwipeRefreshLayout.setRefreshing(false);
        });
    }

    private void issueCall(String header, String issue) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.issueSearch(header, issue)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(IssueResult::getItems)
                .subscribe(issueList -> {
                    if (issueList.size() > 0) {
                        IssueAdapter searchIssuesAdapter = new IssueAdapter(issueList);
                        getBinding().searchIssuesRecyclerView.setAdapter(searchIssuesAdapter);
                    } else {
                        getBinding().noIssueResultsTextView.setVisibility(View.VISIBLE);
                    }
                }, error -> {
                    getBinding().noIssueResultsTextView.setVisibility(View.VISIBLE);
                    Timber.i(error.getMessage());
                });
    }

}
