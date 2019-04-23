package mb00.android.codehub.ui.gist.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.gist.view.GistActivity


@Subcomponent(modules = [
    GistActivityModule::class,
    GistFragmentProvider::class
])
interface GistActivityComponent : AndroidInjector<GistActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<GistActivity>()

}
