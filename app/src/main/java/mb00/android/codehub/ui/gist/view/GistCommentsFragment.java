package mb00.android.codehub.ui.gist.view;

import mb00.android.codehub.R;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.api.model.Comment;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.universaladapter.CommentAdapter;
import mb00.android.codehub.ui.gist.adapter.GistFragmentPagerAdapter;

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
 * Fragment containing gist comments; launched from {@link GistFragmentPagerAdapter}
 */

public class GistCommentsFragment extends Fragment {

    //==============================================================================================
    // GistCommentsFragment fields
    //==============================================================================================

    private SharedPreferences preferences;
    private String authHeader;
    private String gistId;

    private RecyclerView gistCommentsRecyclerView;
    private CommentAdapter commentAdapter;
    private TextView noGistCommentsTextView;
    private SwipeRefreshLayout gistCommentsSwipeRefreshLayout;

    //==============================================================================================
    // Fragment / lifecycle methods
    //==============================================================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View gistCommentsView = inflater.inflate(R.layout.fragment_gist_comments, container, false);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        gistId = getArguments().getString(BundleKeys.GIST_ID);

        gistCommentsRecyclerView = (RecyclerView) gistCommentsView.findViewById(R.id.gist_comments_recycler_view);
        noGistCommentsTextView = (TextView) gistCommentsView.findViewById(R.id.no_gist_comments_text_view);
        gistCommentsSwipeRefreshLayout = (SwipeRefreshLayout) gistCommentsView.findViewById(R.id.gist_comments_swipe_refresh_layout);

        gistCommentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        gistCommentsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        gistCommentsSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                gistCommentsCall(authHeader, gistId);
                gistCommentsSwipeRefreshLayout.setRefreshing(false);
            }
        });
        gistCommentsCall(authHeader, gistId);

        return gistCommentsView;
    }

    //==============================================================================================
    // GistCommentsFragment methods
    //==============================================================================================

    private void gistCommentsCall(String header, String gist) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        Call<List<Comment>> call = service.getGistComments(header, gist);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                List<Comment> gistCommentList = response.body();

                if (gistCommentList.size() > 0) {
                    commentAdapter = new CommentAdapter(gistCommentList, getActivity());
                    gistCommentsRecyclerView.setAdapter(commentAdapter);
                } else {
                    noGistCommentsTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }

}
