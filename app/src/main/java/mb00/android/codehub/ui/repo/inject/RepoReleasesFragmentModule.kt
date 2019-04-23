package mb00.android.codehub.ui.repo.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter
import mb00.android.codehub.ui.repo.viewmodel.RepoReleasesViewModel


@Module
class RepoReleasesFragmentModule {

    @Provides
    internal fun provideRepoReleasesViewModel(interactor: RepoInteractor, router: RepoRouter): RepoReleasesViewModel {
        return RepoReleasesViewModel(interactor, router)
    }

}
