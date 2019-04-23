package mb00.android.codehub.ui.home.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.home.view.HomeActivity


@Subcomponent(modules = [
    HomeActivityModule::class,
    HomeFragmentProvider::class
])
interface HomeActivityComponent : AndroidInjector<HomeActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeActivity>()

}
