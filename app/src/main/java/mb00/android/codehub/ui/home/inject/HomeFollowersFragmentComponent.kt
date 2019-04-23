package mb00.android.codehub.ui.home.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.user.view.HomeFollowersFragment


@Subcomponent(modules = [
    HomeFollowersFragmentModule::class
])
interface HomeFollowersFragmentComponent : AndroidInjector<HomeFollowersFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeFollowersFragment>()

}
