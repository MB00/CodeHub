package mb00.android.codehub.ui.repo.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.repo.view.RepoReleasesFragment


@Subcomponent(modules = [
    RepoReleasesFragmentModule::class
])
interface RepoReleasesFragmentComponent : AndroidInjector<RepoReleasesFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RepoReleasesFragment>()

}
