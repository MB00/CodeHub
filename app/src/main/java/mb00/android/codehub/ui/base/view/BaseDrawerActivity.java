package mb00.android.codehub.ui.base.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mb00.android.codehub.R;
import mb00.android.codehub.api.builder.RetrofitBuilder;
import mb00.android.codehub.api.model.User;
import mb00.android.codehub.api.service.GitHubService;
import mb00.android.codehub.data.BundleKeys;
import mb00.android.codehub.data.NavigationValues;
import mb00.android.codehub.data.PreferenceKeys;
import mb00.android.codehub.ui.about.view.AboutActivity;
import mb00.android.codehub.ui.base.viewmodel.BaseViewModel;
import mb00.android.codehub.ui.home.view.HomeActivity;
import mb00.android.codehub.ui.login.view.LoginActivity;
import mb00.android.codehub.ui.settings.view.SettingsActivity;
import mb00.android.codehub.ui.user.view.UserActivity;
import retrofit2.Retrofit;
import timber.log.Timber;


public abstract class BaseDrawerActivity<B extends ViewDataBinding, V extends BaseViewModel> extends BaseBindingActivity<B, V> {

    private String userName;
    private String userAvatarUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupNavigationDrawer();
    }

    public void setupNavigationDrawer() {
        final View rootView = getWindow().getDecorView().getRootView();

        final DrawerLayout navigationDrawer = rootView.findViewById(R.id.drawer_layout);
        NavigationView navigationView = rootView.findViewById(R.id.drawer_navigation_view);

        ImageView userAvatarImageView = navigationView.getHeaderView(0).findViewById(R.id.drawer_user_avatar_image_view);
        TextView userNameTextView = navigationView.getHeaderView(0).findViewById(R.id.drawer_user_name_text_view);

        SharedPreferences preferences = getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE);
        String authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "");
        userName = preferences.getString(PreferenceKeys.USER_NAME, "");
        avatarCall(authHeader, userName, userAvatarImageView);
        userNameTextView.setText(userName);

        navigationView.setNavigationItemSelectedListener(item -> {
            //item.setChecked(true);
            navigationDrawer.closeDrawers();
            Bundle userBundle = new Bundle();
            Intent userActivityIntent = new Intent(this, UserActivity.class);

            switch (item.getItemId()) {
                case R.id.drawer_home_button:
                    Intent homeActivityIntent = new Intent(this, HomeActivity.class);
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
                    AlertDialog.Builder logoutDialog = new AlertDialog.Builder(this)
                            .setTitle(R.string.sign_out_confirmation)
                            .setNegativeButton(R.string.no, null)
                            .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                                SharedPreferences.Editor preferenceEditor = getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE).edit();
                                preferenceEditor.putBoolean(PreferenceKeys.SIGNED_IN, false);
                                preferenceEditor.putString(PreferenceKeys.USER_NAME, "");
                                preferenceEditor.putString(PreferenceKeys.AUTH_HEADER, "");
                                preferenceEditor.apply();
                                Intent loginActivityIntent = new Intent(this, LoginActivity.class);
                                startActivity(loginActivityIntent);
                            });
                    logoutDialog.show();
                    break;
                case R.id.drawer_settings_button:
                    Intent settingsIntent = new Intent(this, SettingsActivity.class);
                    startActivity(settingsIntent);
                    break;
                case R.id.drawer_about_button:
                    Intent aboutIntent = new Intent(this, AboutActivity.class);
                    startActivity(aboutIntent);
                    break;
            }
            return true;
        });
    }

    private void avatarCall(String header, String user, final ImageView userAvatarImageView) {
        Retrofit retrofit = RetrofitBuilder.getInstance();
        GitHubService service = retrofit.create(GitHubService.class);

        service.getUserOverview(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(User::getAvatarUrl)
                .subscribe(userAvatarUrl -> {
                    Glide.with(BaseDrawerActivity.this).load(userAvatarUrl).into(userAvatarImageView);
                }, error -> Timber.e(error.getMessage()));
    }

}
