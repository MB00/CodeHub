package mb00.android.codehub.ui.repo.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.repo.view.RepoPullRequestsFragment


@Subcomponent(modules = [
    RepoPullRequestsFragmentModule::class
])
interface RepoPullRequestsFragmentComponent : AndroidInjector<RepoPullRequestsFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RepoPullRequestsFragment>()

}
