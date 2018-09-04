package mb00.android.codehub.ui.user.view;

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
import mb00.android.codehub.databinding.FragmentUserPulseBinding;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.universaladapter.PulseAdapter;
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter;
import mb00.android.codehub.ui.user.viewmodel.UserPulseViewModel;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Fragment containing user pulse; launched from {@link UserFragmentPagerAdapter}
 */

public class UserPulseFragment extends BaseBindingFragment<FragmentUserPulseBinding, UserPulseViewModel> {

    private String userName;
    private String authHeader;

    @Override
    protected int layout() {
        return R.layout.fragment_user_pulse;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);

        if (getArguments() != null) {
            userName = getArguments().getString(BundleKeys.USER_NAME);
        } else {
            userName = preferences.getString(PreferenceKeys.USER_NAME, "");
        }
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
    }

    @Override
    public void onStart() {
        super.onStart();

        initRecyclerView();

        //getViewModel().getUserPulse(authHeader, "MB00");

        getBinding().userPulseSwipeRefreshLayout.setOnRefreshListener(() -> {
            userPulseCall(authHeader, userName);
            getBinding().userPulseSwipeRefreshLayout.setRefreshing(false);
        });
        userPulseCall(authHeader, userName);
    }

    private void initRecyclerView() {
        getBinding().userPulseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getBinding().userPulseRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void userPulseCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getUserPulse(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userPulse -> {
                    if (userPulse.size() > 0) {
                        PulseAdapter pulseAdapter = new PulseAdapter(userPulse, getActivity());
                        getBinding().userPulseRecyclerView.setAdapter(pulseAdapter);
                    } else {
                        getBinding().noUserPulseTextView.setVisibility(View.VISIBLE);
                    }
                }, error -> Timber.i(error.getMessage()));
    }

}
