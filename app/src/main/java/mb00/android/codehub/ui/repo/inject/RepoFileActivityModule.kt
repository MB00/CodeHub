package mb00.android.codehub.ui.repo.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.api.manager.ApiCallManager
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.interactor.RepoInteractorImpl
import mb00.android.codehub.ui.repo.router.RepoRouter
import mb00.android.codehub.ui.repo.router.RepoRouterImpl
import mb00.android.codehub.ui.repo.view.RepoFileActivity
import mb00.android.codehub.ui.repo.viewmodel.RepoFileViewModel


@Module
class RepoFileActivityModule {

    @Provides
    internal fun provideRepoInteractor(apiCallManager: ApiCallManager): RepoInteractor {
        return RepoInteractorImpl(apiCallManager)
    }

    @Provides
    internal fun provideRepoRouter(activity: RepoFileActivity): RepoRouter {
        return RepoRouterImpl(activity)
    }

    @Provides
    internal fun provideRepoViewModel(interactor: RepoInteractor, router: RepoRouter): RepoFileViewModel {
        return RepoFileViewModel(interactor, router)
    }

}
