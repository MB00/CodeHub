package mb00.android.codehub.ui.gist.inject

import android.support.v4.app.Fragment

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import mb00.android.codehub.ui.gist.view.GistCommentsFragment
import mb00.android.codehub.ui.gist.view.GistFilesFragment


@Module
abstract class GistFragmentProvider {

    @Binds
    @IntoMap
    @FragmentKey(GistFilesFragment::class)
    internal abstract fun bindGistFilesFragment(builder: GistFilesFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(GistCommentsFragment::class)
    internal abstract fun bindGistCommentsFragment(builder: GistCommentsFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

}
