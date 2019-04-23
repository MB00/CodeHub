package mb00.android.codehub.ui.repo.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.repo.view.RepoContributorsFragment


@Subcomponent(modules = [
    RepoContributorsFragmentModule::class
])
interface RepoContributorsFragmentComponent : AndroidInjector<RepoContributorsFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RepoContributorsFragment>()

}
