package mb00.android.codehub.ui;

import mb00.android.codehub.R;
import mb00.android.codehub.api.model.User;
import mb00.android.codehub.api.model.UserResult;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.adapter.UserAdapter;

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


public class SearchUsersFragment extends Fragment {

    private SharedPreferences preferences;
    private String authHeader;
    private String user;

    private RecyclerView searchUsersRecyclerView;
    private UserAdapter searchUsersAdapter;
    private TextView noUserResultsTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View searchUsersView = inflater.inflate(R.layout.fragment_search_users, container, false);
        searchUsersRecyclerView = (RecyclerView) searchUsersView.findViewById(R.id.search_users_recycler_view);
        searchUsersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchUsersRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        noUserResultsTextView = (TextView) searchUsersView.findViewById(R.id.no_user_results_text_view);

        user = getArguments().getString(BundleKeys.SEARCH_QUERY_KEY);
        userCall(authHeader, user);

        return searchUsersView;
    }

    private void userCall(String header, String user) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<UserResult> call = service.userSearch(header, user);

        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                if (response.isSuccessful()) {
                    List<User> userList = response.body().getItems();

                    if (userList.size() > 0) {
                        searchUsersAdapter = new UserAdapter(userList);
                        searchUsersRecyclerView.setAdapter(searchUsersAdapter);
                    } else {
                        noUserResultsTextView.setVisibility(View.VISIBLE);
                    }
                } else {
                    noUserResultsTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {

            }
        });
    }

}
