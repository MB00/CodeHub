package mb00.android.codehub.ui.repo.router;

import android.support.v7.app.AppCompatActivity;


public class RepoRouterImpl implements RepoRouter {

    private final AppCompatActivity activity;

    public RepoRouterImpl(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void finishRepoActivity() {
        activity.finish();
    }

}
