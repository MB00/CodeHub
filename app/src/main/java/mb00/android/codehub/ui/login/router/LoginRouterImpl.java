package mb00.android.codehub.ui.login.router;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import mb00.android.codehub.ui.home.view.HomeActivity;


public class LoginRouterImpl implements LoginRouter {

    private final AppCompatActivity activity;

    public LoginRouterImpl(final AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void loadHomeActivity() {
        Intent homeActivityIntent = new Intent(activity, HomeActivity.class);
        activity.startActivity(homeActivityIntent);
    }

}
