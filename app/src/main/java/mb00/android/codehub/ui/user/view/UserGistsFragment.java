package mb00.android.codehub.ui.user.view;

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
import mb00.android.codehub.api.model.Gist;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.gist.adapter.GistAdapter;
import mb00.android.codehub.ui.user.adapter.UserFragmentPagerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Fragment containing user gists; launched from {@link UserFragmentPagerAdapter}
 */

public class UserGistsFragment extends Fragment {

    //==============================================================================================
    // UserGistsFragment fields
    //==============================================================================================

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;

    private RecyclerView userGistsRecyclerView;
    private GistAdapter searchGistsAdapter;
    private TextView noGistsTextView;
    private SwipeRefreshLayout userGistsSwipeRefreshLayout;

    //==============================================================================================
    // Fragment / lifecycle methods
    //==============================================================================================

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

        userGistsRecyclerView = userGistView.findViewById(R.id.user_gists_recycler_view);
        noGistsTextView = userGistView.findViewById(R.id.no_gists_text_view);
        userGistsSwipeRefreshLayout = userGistView.findViewById(R.id.user_gists_swipe_refresh_layout);

        userGistsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        userGistsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        userGistsSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userGistsCall(authHeader, userName);
                userGistsSwipeRefreshLayout.setRefreshing(false);
            }
        });
        userGistsCall(authHeader, userName);

        return userGistView;
    }

    //==============================================================================================
    // UserGistsFragment methods
    //==============================================================================================

    private void userGistsCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Gist>> call = service.getUserGists(header, user);

        call.enqueue(new Callback<List<Gist>>() {
            @Override
            public void onResponse(Call<List<Gist>> call, Response<List<Gist>> response) {
                List<Gist> userGistList = response.body();

                if (userGistList.size() > 0) {
                    searchGistsAdapter = new GistAdapter(userGistList);
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
