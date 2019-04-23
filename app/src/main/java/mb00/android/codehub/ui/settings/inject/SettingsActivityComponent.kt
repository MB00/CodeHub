package mb00.android.codehub.ui.settings.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.settings.view.SettingsActivity


@Subcomponent(modules = [
    SettingsActivityModule::class
])
interface SettingsActivityComponent : AndroidInjector<SettingsActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SettingsActivity>()

}
