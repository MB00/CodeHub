package mb00.android.codehub.ui.repo.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter
import mb00.android.codehub.ui.repo.viewmodel.RepoContributorsViewModel


@Module
class RepoContributorsFragmentModule {

    @Provides
    internal fun provideRepoContributorsViewModel(interactor: RepoInteractor, router: RepoRouter): RepoContributorsViewModel {
        return RepoContributorsViewModel(interactor, router)
    }

}
