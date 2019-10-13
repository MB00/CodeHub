package mb00.android.codehub.ui.login.interactor

import io.reactivex.Single
import mb00.android.codehub.api.manager.ApiCallManager
import mb00.android.codehub.api.model.AccessToken

class LoginInteractorImpl(private val apiCallManager: ApiCallManager) : LoginInteractor {

    override fun loadAccessCode(clientId: String, clientSecret: String, code: String): Single<AccessToken> {
        return apiCallManager.loadAccess(clientId, clientSecret, code)
    }

}