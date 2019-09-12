package mb00.android.codehub.ui.gist.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.api.manager.ApiCallManager
import mb00.android.codehub.ui.gist.interactor.GistInteractor
import mb00.android.codehub.ui.gist.interactor.GistInteractorImpl
import mb00.android.codehub.ui.gist.router.GistRouter
import mb00.android.codehub.ui.gist.router.GistRouterImpl
import mb00.android.codehub.ui.gist.view.GistActivity
import mb00.android.codehub.ui.gist.viewmodel.GistViewModel


@Module(includes = [GistActivityFragmentsModule::class])
class GistActivityModule {

    @Provides
    internal fun provideGistInteractor(apiCallManager: ApiCallManager): GistInteractor {
        return GistInteractorImpl(apiCallManager)
    }

    @Provides
    internal fun provideGistRouter(activity: GistActivity): GistRouter {
        return GistRouterImpl(activity)
    }

    @Provides
    internal fun provideGistViewModel(interactor: GistInteractor, router: GistRouter): GistViewModel {
        return GistViewModel(interactor, router)
    }

}
