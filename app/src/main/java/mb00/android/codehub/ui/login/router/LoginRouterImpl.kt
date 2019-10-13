package mb00.android.codehub.ui.login.router

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import mb00.android.codehub.api.manager.ApiCallManager
import mb00.android.codehub.ui.home.view.HomeActivity
import timber.log.Timber
import javax.inject.Inject


class LoginRouterImpl(private val activity: AppCompatActivity) : LoginRouter {

    val clientId = "726371a4fd9ecc60ad59"
    val clientSecret = "f3c17f84f1644028df458971b361f9331375cfe5"
    val redirectUri = "codehub://callback"

    @Inject
    lateinit var apiCallManager: ApiCallManager


    override fun loadHomeActivity() {
        val homeActivityIntent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(homeActivityIntent)
    }

    override fun loginOAuth() {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize" + "?client_id=" + clientId))
        activity.startActivity(intent)

        val uri = intent.data

        uri.let {
            Timber.i("main: on resume uri = $it")

            val code = it?.getQueryParameter("code")

            Timber.i("main: code = $code")

            apiCallManager.loadAccess(clientId, clientSecret, code ?: "").subscribe { token ->
                Timber.i("main: access token = $it / $token")
            }

        }
    }

}
