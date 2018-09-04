package mb00.android.codehub.ui.main.view;

import android.os.Bundle;

import javax.inject.Inject;

import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.logic.preferences.PreferenceManager;
import mb00.android.codehub.ui.base.view.BaseActivity;
import mb00.android.codehub.ui.home.view.HomeActivity;
import mb00.android.codehub.ui.login.view.LoginActivity;
import mb00.android.codehub.ui.main.router.MainRouter;

/**
 * Program execution starts here
 * Launches {@link HomeActivity} if {@link PreferenceKeys .SIGNED_IN == true}
 * Launches {@link LoginActivity} otherwise
 */

public class MainActivity extends BaseActivity {

    @Inject
    PreferenceManager preferenceManager;

    @Inject
    MainRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (preferenceManager.isSignedIn()) {
            router.loadHomeActivity();
        } else {
            router.loadLoginActivity();
        }
    }

}
