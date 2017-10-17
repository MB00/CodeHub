package mb00.android.codehub.ui;

import mb00.android.codehub.R;
import mb00.android.codehub.api.RetrofitBuilder;
import mb00.android.codehub.api.model.User;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.NavigationValues;
import mb00.android.codehub.data.PreferenceKeys;

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
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class NavigationDrawerSetup {

    private static String userName;
    private static String userAvatarUrl;

    public static void setupNavigationDrawer(final Context context) {
        final DrawerLayout navigationDrawer = (DrawerLayout) ((Activity)context).findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) ((Activity)context).findViewById(R.id.drawer_navigation_view);

        ImageView userAvatarImageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.drawer_user_avatar_image_view);
        TextView userNameTextView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.drawer_user_name_text_view);

        SharedPreferences preferences = context.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        String authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userName = preferences.getString(PreferenceKeys.USER_NAME, "");
        avatarCall(authHeader, userName, context, userAvatarImageView);
        userNameTextView.setText(userName);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item.setChecked(true);
                navigationDrawer.closeDrawers();
                Bundle userBundle = new Bundle();
                Intent userActivityIntent = new Intent(context, UserActivity.class);

                switch (item.getItemId()) {
                    case R.id.drawer_home_button:
                        Intent homeActivityIntent = new Intent(context, HomeActivity.class);
                        context.startActivity(homeActivityIntent);
                        break;
                    case R.id.drawer_profile_button:
                        userBundle.putString(BundleKeys.USER_NAME, userName);
                        userBundle.putInt(BundleKeys.VIEW_PAGER_POSITION, NavigationValues.USER_OVERVIEW);
                        userActivityIntent.putExtras(userBundle);
                        context.startActivity(userActivityIntent);
                        break;
                    case R.id.drawer_repos_button:
                        userBundle.putString(BundleKeys.USER_NAME, userName);
                        userBundle.putInt(BundleKeys.VIEW_PAGER_POSITION, NavigationValues.USER_REPOSITORIES);
                        userActivityIntent.putExtras(userBundle);
                        context.startActivity(userActivityIntent);
                        break;
                    case R.id.drawer_gists_button:
                        userBundle.putString(BundleKeys.USER_NAME, userName);
                        userBundle.putInt(BundleKeys.VIEW_PAGER_POSITION, NavigationValues.USER_GISTS);
                        userActivityIntent.putExtras(userBundle);
                        context.startActivity(userActivityIntent);
                        break;
                    case R.id.drawer_logout_button:
                        AlertDialog.Builder logoutDialog = new AlertDialog.Builder(context)
                                .setTitle(R.string.sign_out_confirmation)
                                .setNegativeButton(R.string.no, null)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        SharedPreferences.Editor preferenceEditor = context.getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE).edit();
                                        preferenceEditor.putString(PreferenceKeys.AUTH_HEADER, "");
                                        preferenceEditor.apply();
                                        Intent loginActivityIntent = new Intent(context, LoginActivity.class);
                                        context.startActivity(loginActivityIntent);
                                    }
                                });
                        logoutDialog.show();
                        break;
                    case R.id.drawer_settings_button:
                        Intent settingsIntent = new Intent(context, SettingsActivity.class);
                        context.startActivity(settingsIntent);
                        break;
                    case R.id.drawer_about_button:
                        Intent aboutIntent = new Intent(context, AboutActivity.class);
                        context.startActivity(aboutIntent);
                        break;
                }
                return true;
            }
        });
    }

    private static void avatarCall(String header, String user, final Context context, final ImageView userAvatarImageView) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<User> call = service.getUserOverview(header, user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userAvatarUrl = response.body().getAvatarUrl();
                Glide.with(context).load(userAvatarUrl).into(userAvatarImageView);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}
