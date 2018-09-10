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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mb00.android.codehub.R;
import mb00.android.codehub.api.builder.RetrofitBuilder;
import mb00.android.codehub.api.model.GistFile;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.gist.adapter.GistFileAdapter;
import mb00.android.codehub.ui.gist.adapter.GistFragmentPagerAdapter;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Fragment containing gist files; launched from {@link GistFragmentPagerAdapter}
 */

public class GistFilesFragment extends Fragment {

    private SharedPreferences preferences;
    private String authHeader;
    private String gistId;

    private RecyclerView gistFilesRecyclerView;
    private GistFileAdapter fileAdapter;
    private TextView noGistFilesTextView;
    private SwipeRefreshLayout gistFilesSwipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View gistFilesView = inflater.inflate(R.layout.fragment_gist_files, container, false);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        gistId = getArguments().getString(BundleKeys.GIST_ID);

        gistFilesRecyclerView = gistFilesView.findViewById(R.id.gist_files_recycler_view);
        noGistFilesTextView = gistFilesView.findViewById(R.id.no_gist_files_text_view);
        gistFilesSwipeRefreshLayout = gistFilesView.findViewById(R.id.gist_files_swipe_refresh_layout);

        gistFilesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        gistFilesRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        gistFilesSwipeRefreshLayout.setOnRefreshListener(() -> {
            gistFilesCall(authHeader, gistId);
            gistFilesSwipeRefreshLayout.setRefreshing(false);
        });
        gistFilesCall(authHeader, gistId);

        return gistFilesView;
    }

    private void gistFilesCall(String header, String gist) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getGistContents(header, gist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(gistFile -> {

                    Map<String, GistFile> fileMap = gistFile.getFiles();
                    List<GistFile> gistFileList = new ArrayList<>(fileMap.values());

                    if (gistFileList.size() > 0) {
                        fileAdapter = new GistFileAdapter(gistFileList, gistFilesRecyclerView, authHeader, gistId);
                        gistFilesRecyclerView.setAdapter(fileAdapter);
                    } else {
                        noGistFilesTextView.setVisibility(View.VISIBLE);
                    }

                }, error -> Timber.e(error.getMessage()));
    }

}
