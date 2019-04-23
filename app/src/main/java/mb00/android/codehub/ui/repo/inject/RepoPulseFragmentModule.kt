package mb00.android.codehub.ui.repo.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.repo.interactor.RepoInteractor
import mb00.android.codehub.ui.repo.router.RepoRouter
import mb00.android.codehub.ui.repo.viewmodel.RepoPulseViewModel


@Module
class RepoPulseFragmentModule {

    @Provides
    internal fun provideRepoPulseViewModel(interactor: RepoInteractor, router: RepoRouter): RepoPulseViewModel {
        return RepoPulseViewModel(interactor, router)
    }

}
