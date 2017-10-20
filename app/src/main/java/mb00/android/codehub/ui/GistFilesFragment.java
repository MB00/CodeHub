package mb00.android.codehub.ui;

import mb00.android.codehub.R;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.api.model.Code;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.PreferenceKeys;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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


public class GistFilesFragment extends Fragment{

    private SharedPreferences preferences;
    private String authHeader;
    private String gistId;

    private RecyclerView gistFilesRecyclerView;
    private TextView noGistFilesTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View gistFilesView = inflater.inflate(R.layout.fragment_gist_files, container, false);

        preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");

        gistFilesRecyclerView = (RecyclerView) gistFilesView.findViewById(R.id.gist_files_recycler_view);
        noGistFilesTextView = (TextView) gistFilesView.findViewById(R.id.no_gist_files_text_view);

        gistFilesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        gistFilesRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        gistFilesCall(authHeader, gistId);

        return gistFilesView;
    }

    private void gistFilesCall(String header, String gist) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        Call<List<Code>> call = service.getGistContents(header, gist);

        call.enqueue(new Callback<List<Code>>() {
            @Override
            public void onResponse(Call<List<Code>> call, Response<List<Code>> response) {
                List<Code> gistFileList = response.body();
            }

            @Override
            public void onFailure(Call<List<Code>> call, Throwable t) {

            }
        });
    }

}
