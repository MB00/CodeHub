package mb00.android.codehub.ui.user.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.user.view.UserFollowingFragment


@Subcomponent(modules = [
    UserFollowingFragmentModule::class
])
interface UserFollowingFragmentComponent : AndroidInjector<UserFollowingFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<UserFollowingFragment>()

}
