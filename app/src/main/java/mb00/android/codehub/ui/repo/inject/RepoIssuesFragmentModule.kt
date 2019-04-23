package mb00.android.codehub.ui.repo.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter
import mb00.android.codehub.ui.repo.viewmodel.RepoIssuesViewModel


@Module
class RepoIssuesFragmentModule {

    @Provides
    internal fun provideRepoIssuesViewModel(interactor: RepoInteractor, router: RepoRouter): RepoIssuesViewModel {
        return RepoIssuesViewModel(interactor, router)
    }

}
