package mb00.android.codehub.ui.login.interactor

import io.reactivex.Single
import mb00.android.codehub.api.model.AccessToken

interface LoginInteractor {

    fun loadAccessCode(clientId: String, clientSecret: String, code: String): Single<AccessToken>

}