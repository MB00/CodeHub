package mb00.android.codehub.ui;

import mb00.android.codehub.R;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.api.model.Comment;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.PreferenceKeys;

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


public class GistCommentsFragment extends Fragment {

    private SharedPreferences preferences;
    private String authHeader;
    private String gistId;

    private RecyclerView gistCommentsRecyclerView;
    private TextView noGistCommentsTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View gistCommentsView = inflater.inflate(R.layout.fragment_gist_comments, container, false);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");

        gistCommentsRecyclerView = (RecyclerView) gistCommentsView.findViewById(R.id.gist_comments_recycler_view);
        noGistCommentsTextView = (TextView) gistCommentsView.findViewById(R.id.no_gist_comments_text_view);

        gistCommentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        gistCommentsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        gistCommentsCall(authHeader, gistId);

        return gistCommentsView;
    }

    private void gistCommentsCall(String header, String gist) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        Call<List<Comment>> call = service.getGistComments(header, gist);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                List<Comment> gistCommentList = response.body();
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }

}