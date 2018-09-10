package mb00.android.codehub.ui.repo.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mb00.android.codehub.R;
import mb00.android.codehub.api.builder.RetrofitBuilder;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.databinding.FragmentRepoReadmeBinding;
import mb00.android.codehub.logic.utils.Base64Decoder;
import mb00.android.codehub.logic.utils.MarkdownParser;
import mb00.android.codehub.ui.base.view.BaseBindingFragment;
import mb00.android.codehub.ui.repo.adapter.RepoFragmentPagerAdapter;
import mb00.android.codehub.ui.repo.viewmodel.RepoReadmeViewModel;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Fragment containing repository readme; launched from {@link RepoFragmentPagerAdapter}
 */

public class RepoReadmeFragment extends BaseBindingFragment<FragmentRepoReadmeBinding, RepoReadmeViewModel> {

    private String authHeader;
    private String userName;
    private String repoName;

    @Override
    protected int layout() {
        return R.layout.fragment_repo_readme;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getActivity().getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userName = getArguments().getString(BundleKeys.USER_NAME);
        repoName = getArguments().getString(BundleKeys.REPO_NAME);
    }

    @Override
    public void onStart() {
        super.onStart();

        repoReadmeCall(authHeader, userName, repoName);
    }

    private void repoReadmeCall(String header, String user, String repo) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getRepoReadme(header, user, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoReadme -> {
                    String readmeMarkdown = Base64Decoder.decodeBase64(repoReadme.getContent());
                    String readmeParsed = MarkdownParser.parseMarkdown(readmeMarkdown);
                    getBinding().repoReadmeTextView.setText(Html.fromHtml(readmeParsed));
                }, error -> Timber.e(error.getMessage()));
    }

}
