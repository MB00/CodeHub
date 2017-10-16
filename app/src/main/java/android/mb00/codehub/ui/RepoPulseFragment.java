package android.mb00.codehub.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.mb00.codehub.R;
import android.mb00.codehub.api.model.Pulse;
import android.mb00.codehub.api.service.GitHubService;
import android.mb00.codehub.data.BundleKeys;
import android.mb00.codehub.api.RetrofitBuilder;
import android.mb00.codehub.data.PreferenceKeys;
import android.mb00.codehub.ui.adapter.PulseAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


public class RepoPulseFragment extends Fragment {

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;
    private String repoName;

    private RecyclerView repoPulseRecyclerView;
    private PulseAdapter pulseAdapter;
    private TextView noRepoPulseTextView;

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

        repoPulseRecyclerView = (RecyclerView) repoPulseView.findViewById(R.id.repo_pulse_recycler_view);
        noRepoPulseTextView = (TextView) repoPulseView.findViewById(R.id.no_repo_pulse_text_view);

        repoPulseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        repoPulseRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        repoPulseCall(authHeader, userName, repoName);

        return repoPulseView;
    }

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
