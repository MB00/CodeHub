package mb00.android.codehub.ui.gist.view;

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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mb00.android.codehub.R;
import mb00.android.codehub.api.builder.RetrofitBuilder;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.gist.adapter.GistFragmentPagerAdapter;
import mb00.android.codehub.ui.universaladapter.CommentAdapter;
import retrofit2.Retrofit;

/**
 * Fragment containing gist comments; launched from {@link GistFragmentPagerAdapter}
 */

public class GistCommentsFragment extends Fragment {

    private SharedPreferences preferences;
    private String authHeader;
    private String gistId;

    private RecyclerView gistCommentsRecyclerView;
    private CommentAdapter commentAdapter;
    private TextView noGistCommentsTextView;
    private SwipeRefreshLayout gistCommentsSwipeRefreshLayout;

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

        gistCommentsRecyclerView = gistCommentsView.findViewById(R.id.gist_comments_recycler_view);
        noGistCommentsTextView = gistCommentsView.findViewById(R.id.no_gist_comments_text_view);
        gistCommentsSwipeRefreshLayout = gistCommentsView.findViewById(R.id.gist_comments_swipe_refresh_layout);

        gistCommentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        gistCommentsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        gistCommentsSwipeRefreshLayout.setOnRefreshListener(() -> {
            gistCommentsCall(authHeader, gistId);
            gistCommentsSwipeRefreshLayout.setRefreshing(false);
        });
        gistCommentsCall(authHeader, gistId);

        return gistCommentsView;
    }

    private void gistCommentsCall(String header, String gist) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getGistComments(header, gist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(gistCommentList -> {
                    if (gistCommentList.size() > 0) {
                        commentAdapter = new CommentAdapter(gistCommentList, getActivity());
                        gistCommentsRecyclerView.setAdapter(commentAdapter);
                    } else {
                        noGistCommentsTextView.setVisibility(View.VISIBLE);
                    }
                });
    }

}
