package mb00.android.codehub.ui;

import android.content.Intent;
import android.content.SharedPreferences;

import mb00.android.codehub.api.model.User;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.data.PreferenceKeys;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginActivity extends AppCompatActivity {

    private String username;
    private String password;

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mb00.android.codehub.R.layout.activity_login);

        usernameEditText = (EditText) findViewById(mb00.android.codehub.R.id.username_edit_text);
        passwordEditText = (EditText) findViewById(mb00.android.codehub.R.id.password_edit_text);
        Button loginButton = (Button) findViewById(mb00.android.codehub.R.id.sign_in_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();

                Retrofit retrofit = RetrofitBuilder.getInstance();
                GitHubService service = retrofit.create(GitHubService.class);

                String loginInfo = username + ":" + password;
                final String authenticationHeader = "Basic " + Base64.encodeToString(loginInfo.getBytes(), Base64.NO_WRAP);
                Call<User> call = service.getUserOverview(authenticationHeader, username);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                       if (response.isSuccessful()) {
                           SharedPreferences.Editor preferenceEditor = getSharedPreferences(PreferenceKeys.PREFERENCES, MODE_PRIVATE).edit();
                           username = response.body().getLogin();
                           preferenceEditor.putString(PreferenceKeys.USER_NAME, username);
                           preferenceEditor.putString(PreferenceKeys.AUTH_HEADER, authenticationHeader);
                           preferenceEditor.putBoolean(PreferenceKeys.SIGNED_IN, true);
                           preferenceEditor.apply();

                           Intent homeActivityIntent = new Intent(LoginActivity.this, HomeActivity.class);
                           startActivity(homeActivityIntent);
                       } else {
                           Toast.makeText(LoginActivity.this, mb00.android.codehub.R.string.login_failure, Toast.LENGTH_SHORT).show();
                       }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });
    }

}
