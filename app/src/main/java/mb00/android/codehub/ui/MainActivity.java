package mb00.android.codehub.ui;

import mb00.android.codehub.data.PreferenceKeys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Program execution starts here
 * Launches {@link HomeActivity} if {@link PreferenceKeys .SIGNED_IN == true}
 * Launches {@link LoginActivity} otherwise
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences(PreferenceKeys.PREFERENCES, MODE_PRIVATE);
        boolean signedIn = preferences.getBoolean(PreferenceKeys.SIGNED_IN, false);

        if (signedIn) {
            Intent homeActivityIntent = new Intent(this, HomeActivity.class);
            startActivity(homeActivityIntent);
        } else {
            Intent loginActivityIntent = new Intent(this, LoginActivity.class);
            startActivity(loginActivityIntent);
        }
    }

}
