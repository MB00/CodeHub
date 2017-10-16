package mb00.android.codehub.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import mb00.android.codehub.data.PreferenceKeys;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


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
