package mb00.android.codehub.ui.home.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.user.view.HomeFollowingFragment


@Subcomponent(modules = [
    HomeFollowingFragmentModule::class
])
interface HomeFollowingFragmentComponent : AndroidInjector<HomeFollowingFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeFollowingFragment>()

}
