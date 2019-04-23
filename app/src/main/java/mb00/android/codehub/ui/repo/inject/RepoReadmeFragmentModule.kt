package mb00.android.codehub.ui.repo.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter
import mb00.android.codehub.ui.repo.viewmodel.RepoReadmeViewModel


@Module
class RepoReadmeFragmentModule {

    @Provides
    internal fun provideRepoReadmeViewModel(interactor: RepoInteractor, router: RepoRouter): RepoReadmeViewModel {
        return RepoReadmeViewModel(interactor, router)
    }

}
