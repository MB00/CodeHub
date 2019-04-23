package mb00.android.codehub.ui.about.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.about.view.AboutActivity


@Subcomponent(modules = [
    AboutActivityModule::class
])
interface AboutActivityComponent : AndroidInjector<AboutActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<AboutActivity>()

}
