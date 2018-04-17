package mb00.android.codehub.ui.repo.view;

import mb00.android.codehub.R;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.api.model.Readme;
import mb00.android.codehub.api.parser.MarkdownParser;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.api.Base64Decoder;
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Fragment containing repository readme; launched from {@link RepoFragmentPagerAdapter}
 */

public class RepoReadmeFragment extends Fragment {

    //==============================================================================================
    // RepoReadmeFragment fields
    //==============================================================================================

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;
    private String repoName;

    private TextView readmeTextView;
    private TextView noReadmeTextView;

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
        View readmeView = inflater.inflate(R.layout.fragment_repo_readme, container, false);
        readmeTextView = readmeView.findViewById(R.id.repo_readme_text_view);
        noReadmeTextView = readmeView.findViewById(R.id.no_repo_readme_text_view);
        repoReadmeCall(authHeader, userName, repoName);
        return readmeView;
    }

    //==============================================================================================
    // RepoReadmeFragment methods
    //==============================================================================================

    private void repoReadmeCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<Readme> call = service.getRepoReadme(header, user, repo);

        call.enqueue(new Callback<Readme>() {
            @Override
            public void onResponse(Call<Readme> call, Response<Readme> response) {
                if (response.isSuccessful()) {
                    String readmeMarkdown = Base64Decoder.decodeBase64(response.body().getContent());
                    String readmeParsed = MarkdownParser.parseMarkdown(readmeMarkdown);
                    readmeTextView.setText(Html.fromHtml(readmeParsed));
                } else {
                    noReadmeTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Readme> call, Throwable t) {

            }
        });
    }

}