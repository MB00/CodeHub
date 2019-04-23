package mb00.android.codehub.ui.repo.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter
import mb00.android.codehub.ui.repo.viewmodel.RepoCodeViewModel


@Module
class RepoCodeFragmentModule {

    @Provides
    internal fun provideRepoCodeViewModel(interactor: RepoInteractor, router: RepoRouter): RepoCodeViewModel {
        return RepoCodeViewModel(interactor, router)
    }

}
