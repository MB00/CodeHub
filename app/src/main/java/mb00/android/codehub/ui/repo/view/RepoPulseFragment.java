package mb00.android.codehub.ui.repo.view;

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

import mb00.android.codehub.R;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.api.model.Pulse;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter;
import mb00.android.codehub.ui.universaladapter.PulseAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Fragment containing repository pulse; launched from {@link RepoFragmentPagerAdapter}
 */

public class RepoPulseFragment extends Fragment {

    //==============================================================================================
    // RepoPulseFragment fields
    //==============================================================================================

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;
    private String repoName;

    private RecyclerView repoPulseRecyclerView;
    private PulseAdapter pulseAdapter;
    private TextView noRepoPulseTextView;
    private SwipeRefreshLayout repoPulseSwipeRefreshLayout;

    //==============================================================================================
    // Fragment / lifecycle methods
    //==============================================================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userName = getArguments().getString(BundleKeys.USER_NAME);
        repoName = getArguments().getString(BundleKeys.REPO_NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View repoPulseView = inflater.inflate(R.layout.fragment_repo_pulse, container, false);

        repoPulseRecyclerView = repoPulseView.findViewById(R.id.repo_pulse_recycler_view);
        noRepoPulseTextView = repoPulseView.findViewById(R.id.no_repo_pulse_text_view);
        repoPulseSwipeRefreshLayout = repoPulseView.findViewById(R.id.repo_pulse_swipe_refresh_layout);

        repoPulseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        repoPulseRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        repoPulseSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                repoPulseCall(authHeader, userName, repoName);
                repoPulseSwipeRefreshLayout.setRefreshing(false);
            }
        });
        repoPulseCall(authHeader, userName, repoName);

        return repoPulseView;
    }

    //==============================================================================================
    // RepoPulseFragment methods
    //==============================================================================================

    private void repoPulseCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        Call<List<Pulse>> call = service.getRepoPulse(header, user, repo);

        call.enqueue(new Callback<List<Pulse>>() {
            @Override
            public void onResponse(Call<List<Pulse>> call, Response<List<Pulse>> response) {
                List<Pulse> repoPulseList = response.body();

                if (repoPulseList.size() > 0) {
                    pulseAdapter = new PulseAdapter(repoPulseList, getActivity());
                    repoPulseRecyclerView.setAdapter(pulseAdapter);
                } else {
                    noRepoPulseTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Pulse>> call, Throwable t) {

            }
        });
    }

}
