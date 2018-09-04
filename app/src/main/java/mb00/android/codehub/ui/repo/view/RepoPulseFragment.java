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
import mb00.android.codehub.databinding.FragmentRepoPulseBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter;
import mb00.android.codehub.ui.repo.viewmodel.RepoPulseViewModel;
import mb00.android.codehub.ui.universaladapter.PulseAdapter;
import retrofit2.Retrofit;

/**
 * Fragment containing repository pulse; launched from {@link RepoFragmentPagerAdapter}
 */

public class RepoPulseFragment extends BaseBindingFragment<FragmentRepoPulseBinding, RepoPulseViewModel> {

    private String authHeader;
    private String userName;
    private String repoName;

    @Override
    protected int layout() {
        return R.layout.fragment_repo_pulse;
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
        getBinding().repoPulseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().repoPulseRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        getBinding().repoPulseSwipeRefreshLayout.setOnRefreshListener(() -> {
            repoPulseCall(authHeader, userName, repoName);
            getBinding().repoPulseSwipeRefreshLayout.setRefreshing(false);
        });
        repoPulseCall(authHeader, userName, repoName);
    }


    private void repoPulseCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getRepoPulse(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoPulseList -> {
                    if (repoPulseList.size() > 0) {
                        PulseAdapter pulseAdapter = new PulseAdapter(repoPulseList, getActivity());
                        getBinding().repoPulseRecyclerView.setAdapter(pulseAdapter);
                    } else {
                        getBinding().noRepoPulseTextView.setVisibility(View.VISIBLE);
                    }
                }, Throwable::getMessage);
    }

}
