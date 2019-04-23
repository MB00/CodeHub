package mb00.android.codehub.ui.login.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.login.view.LoginActivity


@Subcomponent(modules = [
    LoginActivityModule::class
])
interface LoginActivityComponent : AndroidInjector<LoginActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<LoginActivity>()

}
