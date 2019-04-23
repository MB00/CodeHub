package mb00.android.codehub.ui.user.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.user.view.UserReposFragment


@Subcomponent(modules = [
    UserReposFragmentModule::class
])
interface UserReposFragmentComponent : AndroidInjector<UserReposFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<UserReposFragment>()

}
