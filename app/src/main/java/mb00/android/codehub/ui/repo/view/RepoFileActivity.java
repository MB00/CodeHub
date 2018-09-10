package mb00.android.codehub.ui.repo.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mb00.android.codehub.R;
import mb00.android.codehub.api.builder.RetrofitBuilder;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.databinding.ActivityRepoFileBinding;
import mb00.android.codehub.logic.utils.Base64Decoder;
import mb00.android.codehub.ui.base.view.BaseDrawerActivity;
import mb00.android.codehub.ui.repo.adapter.CodeAdapter;
import mb00.android.codehub.ui.repo.viewmodel.RepoFileViewModel;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Launched from {@link CodeAdapter} if repository file in RecyclerView is clicked
 */

public class RepoFileActivity extends BaseDrawerActivity<ActivityRepoFileBinding, RepoFileViewModel> {

    @Override
    protected int layout() {
        return R.layout.activity_repo_file;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        String authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        String userName = getIntent().getExtras().getString(BundleKeys.USER_NAME);
        String repoName = getIntent().getExtras().getString(BundleKeys.REPO_NAME);
        String fileName = getIntent().getExtras().getString(BundleKeys.FILE_NAME);
        String filePath = getIntent().getExtras().getString(BundleKeys.FILE_PATH);

        getBinding().repoFileBackButton.setOnClickListener(view -> finish());
        getBinding().repoFileTitleTextView.setText(fileName);

        codeFileCall(authHeader, userName, repoName, filePath);
    }

    private void codeFileCall(String header, String user, String repo, String filePath) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getRepoFile(header, user, repo, filePath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoFile -> {
                    String file = Base64Decoder.decodeBase64(repoFile.getContent());
                    getBinding().repoFileTextView.setText(file);
                }, error -> Timber.e(error.getMessage()));
    }

}
