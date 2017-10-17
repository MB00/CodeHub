package mb00.android.codehub.ui;

import mb00.android.codehub.R;
import mb00.android.codehub.api.Base64Decoder;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.api.model.Code;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RepoFileActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private String authHeader;
    private String userName;
    private String repoName;
    private String fileName;
    private String filePath;

    private Toolbar fileToolbar;
    private ImageButton fileBackButton;
    private TextView fileTitleTextView;

    private TextView fileTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_file);

        preferences = getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userName = getIntent().getExtras().getString(BundleKeys.USER_NAME);
        repoName = getIntent().getExtras().getString(BundleKeys.REPO_NAME);
        fileName = getIntent().getExtras().getString(BundleKeys.FILE_NAME);
        filePath = getIntent().getExtras().getString(BundleKeys.FILE_PATH);

        fileToolbar = (Toolbar) findViewById(R.id.toolbar_file);
        fileBackButton = (ImageButton) fileToolbar.findViewById(R.id.file_back_button);
        fileTitleTextView = (TextView) fileToolbar.findViewById(R.id.file_title_text_view);
        fileTextView = (TextView) findViewById(R.id.repo_file_text_view);

        fileBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fileTitleTextView.setText(fileName);

        codeFileCall(authHeader, userName, repoName, filePath);
    }

    private void codeFileCall(String header, String user, String repo, String filePath) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<Code> call = service.getRepoFile(header, user, repo, filePath);

        call.enqueue(new Callback<Code>() {
            @Override
            public void onResponse(Call<Code> call, Response<Code> response) {
                String file = Base64Decoder.decodeBase64(response.body().getContent());
                fileTextView.setText(file);
            }

            @Override
            public void onFailure(Call<Code> call, Throwable t) {

            }
        });
    }

}
