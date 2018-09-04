package mb00.android.codehub.ui.main.router;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import mb00.android.codehub.ui.home.view.HomeActivity;
import mb00.android.codehub.ui.login.view.LoginActivity;

public class MainRouterImpl implements MainRouter {

    private final AppCompatActivity activity;

    public MainRouterImpl(final AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void loadHomeActivity() {
        Intent i = new Intent(activity, HomeActivity.class);
        activity.startActivity(i);
    }

    @Override
    public void loadLoginActivity() {
        Intent i = new Intent(activity, LoginActivity.class);
        activity.startActivity(i);
    }

}
