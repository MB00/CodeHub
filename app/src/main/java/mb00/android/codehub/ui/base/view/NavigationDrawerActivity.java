package mb00.android.codehub.ui.base.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mb00.android.codehub.R;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.api.model.User;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.NavigationValues;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.about.view.AboutActivity;
import mb00.android.codehub.ui.gist.view.GistActivity;
import mb00.android.codehub.ui.home.view.HomeActivity;
import mb00.android.codehub.ui.login.view.LoginActivity;
import mb00.android.codehub.ui.repo.view.RepoActivity;
import mb00.android.codehub.ui.search.view.SearchActivity;
import mb00.android.codehub.ui.settings.view.SettingsActivity;
import mb00.android.codehub.ui.user.view.UserActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Base Activity containing left-side navigation drawer; extended in {@link HomeActivity}
 *                                                                   {@link UserActivity},
 *                                                                   {@link RepoActivity},
 *                                                                   {@link GistActivity},
 *                                                               and {@link SearchActivity}
 */

public class NavigationDrawerActivity extends AppCompatActivity {

    //==============================================================================================
    // NavigationDrawerActivity fields
    //==============================================================================================

    private static String userName;
    private static String userAvatarUrl;

    //==============================================================================================
    // NavigationDrawerActivity methods
    //==============================================================================================

    public void setupNavigationDrawer(final Context context) {
        final DrawerLayout navigationDrawer = ((Activity) context).findViewById(R.id.drawer_layout);
        NavigationView navigationView = ((Activity) context).findViewById(R.id.drawer_navigation_view);

        ImageView userAvatarImageView = navigationView.getHeaderView(0).findViewById(R.id.drawer_user_avatar_image_view);
        TextView userNameTextView = navigationView.getHeaderView(0).findViewById(R.id.drawer_user_name_text_view);

        SharedPreferences preferences = getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        String authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userName = preferences.getString(PreferenceKeys.USER_NAME, "");
        avatarCall(authHeader, userName, userAvatarImageView);
        userNameTextView.setText(userName);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item.setChecked(true);
                navigationDrawer.closeDrawers();
                Bundle userBundle = new Bundle();
                Intent userActivityIntent = new Intent(NavigationDrawerActivity.this, UserActivity.class);

                switch (item.getItemId()) {
                    case R.id.drawer_home_button:
                        Intent homeActivityIntent = new Intent(NavigationDrawerActivity.this, HomeActivity.class);
                        startActivity(homeActivityIntent);
                        break;
                    case R.id.drawer_profile_button:
                        userBundle.putString(BundleKeys.USER_NAME, userName);
                        userBundle.putInt(BundleKeys.VIEW_PAGER_POSITION, NavigationValues.USER_OVERVIEW);
                        userActivityIntent.putExtras(userBundle);
                        startActivity(userActivityIntent);
                        break;
                    case R.id.drawer_repos_button:
                        userBundle.putString(BundleKeys.USER_NAME, userName);
                        userBundle.putInt(BundleKeys.VIEW_PAGER_POSITION, NavigationValues.USER_REPOSITORIES);
                        userActivityIntent.putExtras(userBundle);
                        startActivity(userActivityIntent);
                        break;
                    case R.id.drawer_gists_button:
                        userBundle.putString(BundleKeys.USER_NAME, userName);
                        userBundle.putInt(BundleKeys.VIEW_PAGER_POSITION, NavigationValues.USER_GISTS);
                        userActivityIntent.putExtras(userBundle);
                        startActivity(userActivityIntent);
                        break;
                    case R.id.drawer_logout_button:
                        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(NavigationDrawerActivity.this)
                                .setTitle(R.string.sign_out_confirmation)
                                .setNegativeButton(R.string.no, null)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        SharedPreferences.Editor preferenceEditor = getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE).edit();
                                        preferenceEditor.putBoolean(PreferenceKeys.SIGNED_IN, false);
                                        preferenceEditor.putString(PreferenceKeys.USER_NAME, "");
                                        preferenceEditor.putString(PreferenceKeys.AUTH_HEADER, "");
                                        preferenceEditor.apply();
                                        Intent loginActivityIntent = new Intent(NavigationDrawerActivity.this, LoginActivity.class);
                                        startActivity(loginActivityIntent);
                                    }
                                });
                        logoutDialog.show();
                        break;
                    case R.id.drawer_settings_button:
                        Intent settingsIntent = new Intent(NavigationDrawerActivity.this, SettingsActivity.class);
                        startActivity(settingsIntent);
                        break;
                    case R.id.drawer_about_button:
                        Intent aboutIntent = new Intent(NavigationDrawerActivity.this, AboutActivity.class);
                        startActivity(aboutIntent);
                        break;
                }
                return true;
            }
        });
    }

    private void avatarCall(String header, String user, final ImageView userAvatarImageView) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<User> call = service.getUserOverview(header, user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userAvatarUrl = response.body().getAvatarUrl();
                Glide.with(NavigationDrawerActivity.this).load(userAvatarUrl).into(userAvatarImageView);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}
