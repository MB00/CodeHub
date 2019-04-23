package mb00.android.codehub.ui.user.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.user.view.UserActivity


@Subcomponent(modules = [
    UserActivityModule::class,
    UserFragmentProvider::class
])
interface UserActivityComponent : AndroidInjector<UserActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<UserActivity>()

}
