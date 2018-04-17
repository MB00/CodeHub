package mb00.android.codehub.ui.user.view;

import mb00.android.codehub.R;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.api.model.Pulse;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.universaladapter.PulseAdapter;
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Fragment containing user pulse; launched from {@link UserFragmentPagerAdapter}
 */

public class UserPulseFragment extends Fragment {

    //==============================================================================================
    // UserPulseFragment fields
    //==============================================================================================

    private SharedPreferences preferences;
    private String userName;
    private String authHeader;

    private RecyclerView userPulseRecyclerView;
    private PulseAdapter pulseAdapter;
    private TextView noUserPulseTextView;
    private SwipeRefreshLayout userPulseSwipeRefreshLayout;

    //==============================================================================================
    // Fragment / lifecycle fields
    //==============================================================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);

        if (getArguments() != null) {
            userName = getArguments().getString(BundleKeys.USER_NAME);
        } else {
            userName = preferences.getString(PreferenceKeys.USER_NAME, "");
        }
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View userPulseView = inflater.inflate(R.layout.fragment_user_pulse, container, false);

        userPulseRecyclerView = (RecyclerView) userPulseView.findViewById(R.id.user_pulse_recycler_view);
        noUserPulseTextView = (TextView) userPulseView.findViewById(R.id.no_user_pulse_text_view);
        userPulseSwipeRefreshLayout = (SwipeRefreshLayout) userPulseView.findViewById(R.id.user_pulse_swipe_refresh_layout);

        userPulseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        userPulseRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        userPulseSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userPulseCall(authHeader, userName);
                userPulseSwipeRefreshLayout.setRefreshing(false);
            }
        });
        userPulseCall(authHeader, userName);

        return userPulseView;
    }

    //==============================================================================================
    // UserPulseFragment methods
    //==============================================================================================

    private void userPulseCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Pulse>> call = service.getUserPulse(header, user);

        call.enqueue(new Callback<List<Pulse>>() {
            @Override
            public void onResponse(Call<List<Pulse>> call, Response<List<Pulse>> response) {
                List<Pulse> userPulseList = response.body();

                if (userPulseList.size() > 0) {
                    pulseAdapter = new PulseAdapter(userPulseList, getActivity());
                    userPulseRecyclerView.setAdapter(pulseAdapter);
                } else {
                    noUserPulseTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Pulse>> call, Throwable t) {

            }
        });
    }

}
