package mb00.android.codehub.ui.repo.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.repo.view.RepoActivity


@Subcomponent(modules = [
    RepoActivityModule::class,
    RepoFragmentProvider::class
])
interface RepoActivityComponent : AndroidInjector<RepoActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RepoActivity>()

}
