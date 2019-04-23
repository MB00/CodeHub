package mb00.android.codehub.ui.gist.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.gist.view.GistFilesFragment


@Subcomponent(modules = [
    GistFilesFragmentModule::class
])
interface GistFilesFragmentComponent : AndroidInjector<GistFilesFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<GistFilesFragment>()

}
