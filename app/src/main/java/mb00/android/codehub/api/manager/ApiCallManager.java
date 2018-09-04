package mb00.android.codehub.api.manager;

import android.content.Context;
import android.content.SharedPreferences;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mb00.android.codehub.api.builder.RetrofitBuilder;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.logic.preferences.PreferenceManager;
import mb00.android.codehub.ui.user.view.UserView;
import retrofit2.Retrofit;
import timber.log.Timber;


public class ApiCallManager {

    private String authHeader;
    private String userName;
    private String repoName;

    private Retrofit retrofit;
    private GitHubService service;

    private PreferenceManager preferenceManager;
    private UserView userPulseView;



    public ApiCallManager(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userName = preferences.getString(BundleKeys.USER_NAME, "");
        repoName = preferences.getString(BundleKeys.REPO_NAME, "");

        retrofit = RetrofitBuilder.getInstance();
        service = retrofit.create(GitHubService.class);
    }

    /*public GitHubService getServiceRequest(ApiCallType apiCallType) {
        switch (apiCallType) {
            case LOGIN:
                //return service.getUserOverview()
                break;
            case GIST_COMMENTS:
                break;
            case GIST_FILES:
                break;
            case REPO_CODE:
                break;
            case REPO_COMMITS:
                break;
            case REPO_CONTRIBUTORS:
                break;
            case REPO_ISSUES:
                break;
            case REPO_LICENSE:
                break;
            case REPO_PULL_REQUESTS:
                break;
            case REPO_PULSE:
                break;
            case REPO_README:
                break;
            case REPO_RELEASES:
                break;
            case SEARCH_CODE:
                break;
            case SEARCH_ISSUES:
                break;
            case SEARCH_REPOS:
                break;
            case SEARCH_USERS:
                break;
            case USER_FOLLOWERS:
                break;
            case USER_FOLLOWING:
                break;
            case USER_OVERVIEW:
                break;
            case USER_PULSE:
                break;
            case USER_REPOS:
                break;
            case USER_STARRED:
                break;
        }
    }
*/

    public Observable loadUserPulse(String header, String user) {
        return service.getUserPulse(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
