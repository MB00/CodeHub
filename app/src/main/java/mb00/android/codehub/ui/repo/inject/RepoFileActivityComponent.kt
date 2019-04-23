package mb00.android.codehub.ui.repo.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.repo.view.RepoFileActivity


@Subcomponent(modules = [
    RepoFileActivityModule::class
])
interface RepoFileActivityComponent : AndroidInjector<RepoFileActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RepoFileActivity>()

}
