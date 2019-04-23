package mb00.android.codehub.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mb00.android.codehub.R
import mb00.android.codehub.api.builder.RetrofitBuilder
import mb00.android.codehub.api.service.GitHubService
import mb00.android.codehub.data.PreferenceKeys
import mb00.android.codehub.databinding.ActivityLoginBinding
import mb00.android.codehub.ui.base.view.BaseBindingActivity
import mb00.android.codehub.ui.home.view.HomeActivity
import mb00.android.codehub.ui.login.viewmodel.LoginViewModel
import mb00.android.codehub.ui.main.view.MainActivity

/**
 * Launched from [MainActivity] if [.SIGNED_IN == false][PreferenceKeys]
 * Once logged in, PreferenceKeys.SIGNED_IN is set to true, and [HomeActivity] is launched
 */

class LoginActivity : BaseBindingActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun layout(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            val retrofit = RetrofitBuilder.instance
            val service = retrofit.create(GitHubService::class.java)

            val loginInfo = "$username:$password"
            val authenticationHeader = "Basic " + Base64.encodeToString(loginInfo.toByteArray(), Base64.NO_WRAP)

            service.getUserOverview(authenticationHeader, username)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ user ->
                        val preferenceEditor = getSharedPreferences(PreferenceKeys.PREFERENCES, MODE_PRIVATE).edit()

                        preferenceEditor.putString(PreferenceKeys.USER_NAME, user.login)
                        preferenceEditor.putString(PreferenceKeys.AUTH_HEADER, authenticationHeader)
                        preferenceEditor.putBoolean(PreferenceKeys.SIGNED_IN, true)
                        preferenceEditor.apply()

                        val homeActivityIntent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(homeActivityIntent)
                    }, { Toast.makeText(this@LoginActivity, R.string.login_failure, Toast.LENGTH_SHORT).show() })
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }

}
