package mb00.android.codehub.ui.login.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mb00.android.codehub.R;
import mb00.android.codehub.api.builder.RetrofitBuilder;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.databinding.ActivityLoginBinding;
import mb00.android.codehub.ui.base.view.BaseBindingActivity;
import mb00.android.codehub.ui.home.view.HomeActivity;
import mb00.android.codehub.ui.login.viewmodel.LoginViewModel;
import mb00.android.codehub.ui.main.view.MainActivity;
import retrofit2.Retrofit;

/**
 * Launched from {@link MainActivity} if {@link PreferenceKeys .SIGNED_IN == false}
 * Once logged in, PreferenceKeys.SIGNED_IN is set to true, and {@link HomeActivity} is launched
 */

public class LoginActivity extends BaseBindingActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    protected int layout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBinding().loginButton.setOnClickListener(view -> {
            final String username = getBinding().usernameEditText.getText().toString();
            final String password = getBinding().passwordEditText.getText().toString();

            Retrofit retrofit = RetrofitBuilder.getInstance();
            GitHubService service = retrofit.create(GitHubService.class);

            String loginInfo = username + ":" + password;
            final String authenticationHeader = "Basic " + Base64.encodeToString(loginInfo.getBytes(), Base64.NO_WRAP);

            service.getUserOverview(authenticationHeader, username)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(user -> {
                        SharedPreferences.Editor preferenceEditor = getSharedPreferences(PreferenceKeys.PREFERENCES, MODE_PRIVATE).edit();

                        preferenceEditor.putString(PreferenceKeys.USER_NAME, user.getLogin());
                        preferenceEditor.putString(PreferenceKeys.AUTH_HEADER, authenticationHeader);
                        preferenceEditor.putBoolean(PreferenceKeys.SIGNED_IN, true);
                        preferenceEditor.apply();

                        Intent homeActivityIntent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(homeActivityIntent);

                    }, error -> Toast.makeText(LoginActivity.this, R.string.login_failure, Toast.LENGTH_SHORT).show());
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}
