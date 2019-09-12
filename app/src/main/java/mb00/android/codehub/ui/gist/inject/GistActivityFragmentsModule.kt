package mb00.android.codehub.ui.gist.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mb00.android.codehub.ui.gist.view.GistCommentsFragment
import mb00.android.codehub.ui.gist.view.GistFilesFragment


@Module
abstract class GistActivityFragmentsModule {

    @ContributesAndroidInjector(modules = [GistFilesFragmentModule::class])
    internal abstract fun bindGistFilesFragment(): GistFilesFragment

    @ContributesAndroidInjector(modules = [GistCommentsFragmentModule::class])
    internal abstract fun bindGistCommentsFragment(): GistCommentsFragment

}
