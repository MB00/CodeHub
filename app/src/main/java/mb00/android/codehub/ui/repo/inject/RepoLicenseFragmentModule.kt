package mb00.android.codehub.ui.repo.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter
import mb00.android.codehub.ui.repo.viewmodel.RepoLicenseViewModel


@Module
class RepoLicenseFragmentModule {

    @Provides
    internal fun provideRepoLicenseViewModel(interactor: RepoInteractor, router: RepoRouter): RepoLicenseViewModel {
        return RepoLicenseViewModel(interactor, router)
    }

}
