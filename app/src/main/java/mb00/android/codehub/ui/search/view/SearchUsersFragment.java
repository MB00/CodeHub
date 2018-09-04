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
import mb00.android.codehub.api.model.UserResult;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.databinding.FragmentSearchUsersBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.search.adapter.SearchFragmentPagerAdapter;
import mb00.android.codehub.ui.search.viewmodel.SearchUsersViewModel;
import mb00.android.codehub.ui.user.adapter.UserAdapter;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Fragment containing user search results; launched from {@link SearchFragmentPagerAdapter}
 */

public class SearchUsersFragment extends BaseBindingFragment<FragmentSearchUsersBinding, SearchUsersViewModel> {

    private String authHeader;
    private String user;

    @Override
    protected int layout() {
        return R.layout.fragment_search_users;
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

        user = getArguments().getString(BundleKeys.SEARCH_QUERY_KEY);
        userCall(authHeader, user);
    }


    private void initPreferences() {
        SharedPreferences preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
    }

    private void initRecyclerView() {
        getBinding().searchUsersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().searchUsersRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void initSwipeRefreshListener() {
        getBinding().searchUsersSwipeRefreshLayout.setOnRefreshListener(() -> {
            userCall(authHeader, user);
            getBinding().searchUsersSwipeRefreshLayout.setRefreshing(false);
        });
    }

    private void userCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.userSearch(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(UserResult::getItems)
                .subscribe(userList -> {
                    if (userList.size() > 0) {
                        UserAdapter searchUsersAdapter = new UserAdapter(userList);
                        getBinding().searchUsersRecyclerView.setAdapter(searchUsersAdapter);
                    } else {
                        getBinding().noUserResultsTextView.setVisibility(View.VISIBLE);
                    }
                }, error -> {
                    getBinding().noUserResultsTextView.setVisibility(View.VISIBLE);
                    Timber.i(error.getMessage());
                });
    }

}
