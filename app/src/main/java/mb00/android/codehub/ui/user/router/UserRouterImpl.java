package mb00.android.codehub.ui.user.router;

import android.support.v7.app.AppCompatActivity;


public class UserRouterImpl implements UserRouter {

    private final AppCompatActivity activity;

    public UserRouterImpl(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void finishUserActivity() {
        activity.finish();
    }

}
