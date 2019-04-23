package mb00.android.codehub.ui.gist.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.gist.view.GistCommentsFragment


@Subcomponent(modules = [
    GistCommentsFragmentModule::class
])
interface GistCommentsFragmentComponent : AndroidInjector<GistCommentsFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<GistCommentsFragment>()

}
