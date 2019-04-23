package mb00.android.codehub.ui.home.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.user.view.HomePulseFragment


@Subcomponent(modules = [
    HomePulseFragmentModule::class
])
interface HomePulseFragmentComponent : AndroidInjector<HomePulseFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomePulseFragment>()

}
