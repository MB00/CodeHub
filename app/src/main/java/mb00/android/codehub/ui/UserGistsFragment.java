package mb00.android.codehub.ui;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.Gist;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.adapter.UserGistsAdapter;

import android.content.Context;
import android.content.SharedPreferences;
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


public class UserGistsFragment extends Fragment {

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;

    private RecyclerView userGistsRecyclerView;
    private UserGistsAdapter searchGistsAdapter;
    private TextView noGistsTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userName = getArguments().getString(BundleKeys.USER_NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View userGistView = inflater.inflate(R.layout.fragment_user_gists, container, false);

        userGistsRecyclerView = (RecyclerView) userGistView.findViewById(R.id.user_gists_recycler_view);
        noGistsTextView = (TextView) userGistView.findViewById(R.id.no_gists_text_view);

        userGistsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        userGistsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        userGistsCall(authHeader, userName);

        return userGistView;
    }

    private void userGistsCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Gist>> call = service.getUserGists(header, user);

        call.enqueue(new Callback<List<Gist>>() {
            @Override
            public void onResponse(Call<List<Gist>> call, Response<List<Gist>> response) {
                List<Gist> userGistList = response.body();

                if (userGistList.size() > 0) {
                    searchGistsAdapter = new UserGistsAdapter(userGistList);
                    userGistsRecyclerView.setAdapter(searchGistsAdapter);
                } else {
                    noGistsTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Gist>> call, Throwable t) {

            }
        });
    }

}
