package mb00.android.codehub.ui.user.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.user.view.UserStarredFragment


@Subcomponent(modules = [
    UserStarredFragmentModule::class
])
interface UserStarredFragmentComponent : AndroidInjector<UserStarredFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<UserStarredFragment>()

}
