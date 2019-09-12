package mb00.android.codehub.ui.repo.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.api.manager.ApiCallManager
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.interactor.RepoInteractorImpl
import mb00.android.codehub.ui.repo.router.RepoRouter
import mb00.android.codehub.ui.repo.router.RepoRouterImpl
import mb00.android.codehub.ui.repo.view.RepoActivity
import mb00.android.codehub.ui.repo.viewmodel.RepoViewModel


@Module(includes = [RepoActivityFragmentsModule::class])
class RepoActivityModule {

    @Provides
    internal fun provideRepoInteractor(apiCallManager: ApiCallManager): RepoInteractor {
        return RepoInteractorImpl(apiCallManager)
    }

    @Provides
    internal fun provideRepoRouter(activity: RepoActivity): RepoRouter {
        return RepoRouterImpl(activity)
    }

    @Provides
    internal fun provideRepoViewModel(interactor: RepoInteractor, router: RepoRouter): RepoViewModel {
        return RepoViewModel(interactor, router)
    }

}
