package mb00.android.codehub.ui.user.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.user.view.UserOverviewFragment


@Subcomponent(modules = [
    UserOverviewFragmentModule::class
])
interface UserOverviewFragmentComponent : AndroidInjector<UserOverviewFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<UserOverviewFragment>()

}
