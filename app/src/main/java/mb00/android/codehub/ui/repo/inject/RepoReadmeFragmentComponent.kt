package mb00.android.codehub.ui.repo.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.repo.view.RepoReadmeFragment


@Subcomponent(modules = [
    RepoReadmeFragmentModule::class
])
interface RepoReadmeFragmentComponent : AndroidInjector<RepoReadmeFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RepoReadmeFragment>()

}
