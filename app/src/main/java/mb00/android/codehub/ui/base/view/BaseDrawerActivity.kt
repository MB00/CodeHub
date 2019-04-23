package mb00.android.codehub.ui.base.view

import android.content.Context
import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mb00.android.codehub.R
import mb00.android.codehub.api.builder.RetrofitBuilder
import mb00.android.codehub.api.service.GitHubService
import mb00.android.codehub.data.BundleKeys
import mb00.android.codehub.data.NavigationValues
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.ui.about.view.AboutActivity
import mb00.android.codehub.ui.base.viewmodel.BaseViewModel
import mb00.android.codehub.ui.home.view.HomeActivity
import mb00.android.codehub.ui.login.view.LoginActivity
import mb00.android.codehub.ui.settings.view.SettingsActivity
import mb00.android.codehub.ui.user.view.UserActivity
import timber.log.Timber


abstract class BaseDrawerActivity<B : ViewDataBinding, V : BaseViewModel> : BaseBindingActivity<B, V>() {

    private lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigationDrawer()
    }

    private fun setupNavigationDrawer() {
        val rootView = window.decorView.rootView

        val navigationDrawer = rootView.findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = rootView.findViewById<NavigationView>(R.id.drawer_navigation_view)

        val userAvatarImageView = navigationView.getHeaderView(0).findViewById<ImageView>(R.id.drawer_user_avatar_image_view)
        val userNameTextView = navigationView.getHeaderView(0).findViewById<TextView>(R.id.drawer_user_name_text_view)

        val preferences = getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE)
        val authHeader = preferences.getString(PreferenceKeys.AUTH_HEADER, "")
        userName = preferences.getString(PreferenceKeys.USER_NAME, "")
        avatarCall(authHeader, userName, userAvatarImageView)
        userNameTextView.text = userName

        navigationView.setNavigationItemSelectedListener { item ->
            //item.setChecked(true);
            navigationDrawer.closeDrawers()
            val userBundle = Bundle()
            val userActivityIntent = Intent(this, UserActivity::class.java)

            when (item.itemId) {
                R.id.drawer_home_button -> {
                    val homeActivityIntent = Intent(this, HomeActivity::class.java)
                    startActivity(homeActivityIntent)
                }
                R.id.drawer_profile_button -> {
                    userBundle.putString(BundleKeys.USER_NAME, userName)
                    userBundle.putInt(BundleKeys.VIEW_PAGER_POSITION, NavigationValues.USER_OVERVIEW)
                    userActivityIntent.putExtras(userBundle)
                    startActivity(userActivityIntent)
                }
                R.id.drawer_repos_button -> {
                    userBundle.putString(BundleKeys.USER_NAME, userName)
                    userBundle.putInt(BundleKeys.VIEW_PAGER_POSITION, NavigationValues.USER_REPOSITORIES)
                    userActivityIntent.putExtras(userBundle)
                    startActivity(userActivityIntent)
                }
                R.id.drawer_gists_button -> {
                    userBundle.putString(BundleKeys.USER_NAME, userName)
                    userBundle.putInt(BundleKeys.VIEW_PAGER_POSITION, NavigationValues.USER_GISTS)
                    userActivityIntent.putExtras(userBundle)
                    startActivity(userActivityIntent)
                }
                R.id.drawer_logout_button -> {
                    val logoutDialog = AlertDialog.Builder(this)
                            .setTitle(R.string.sign_out_confirmation)
                            .setNegativeButton(R.string.no, null)
                            .setPositiveButton(R.string.yes) { _, _ ->
                                val preferenceEditor = getSharedPreferences(PreferenceKeys.PREFERENCES, Context.MODE_PRIVATE).edit()
                                preferenceEditor.putBoolean(PreferenceKeys.SIGNED_IN, false)
                                preferenceEditor.putString(PreferenceKeys.USER_NAME, "")
                                preferenceEditor.putString(PreferenceKeys.AUTH_HEADER, "")
                                preferenceEditor.apply()
                                val loginActivityIntent = Intent(this, LoginActivity::class.java)
                                startActivity(loginActivityIntent)
                            }
                    logoutDialog.show()
                }
                R.id.drawer_settings_button -> {
                    val settingsIntent = Intent(this, SettingsActivity::class.java)
                    startActivity(settingsIntent)
                }
                R.id.drawer_about_button -> {
                    val aboutIntent = Intent(this, AboutActivity::class.java)
                    startActivity(aboutIntent)
                }
            }
            true
        }
    }

    private fun avatarCall(header: String, user: String, userAvatarImageView: ImageView) {
        val retrofit = RetrofitBuilder.instance
        val service = retrofit.create(GitHubService::class.java)

        disposables.add(service.getUserOverview(header, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { user -> user.avatarUrl }
                .subscribe({ userAvatarUrl -> Glide.with(this@BaseDrawerActivity).load(userAvatarUrl).into(userAvatarImageView) }, { error -> Timber.e(error.message) }))
    }

}
