package mb00.android.codehub.ui.repo.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.repo.view.RepoPulseFragment


@Subcomponent(modules = [
    RepoPulseFragmentModule::class
])
interface RepoPulseFragmentComponent : AndroidInjector<RepoPulseFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RepoPulseFragment>()

}
