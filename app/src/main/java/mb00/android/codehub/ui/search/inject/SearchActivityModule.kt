package mb00.android.codehub.ui.search.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.api.manager.ApiCallManager
import mb00.android.codehub.ui.search.interactor.SearchInteractor
import mb00.android.codehub.ui.search.interactor.SearchInteractorImpl
import mb00.android.codehub.ui.search.router.SearchRouter
import mb00.android.codehub.ui.search.router.SearchRouterImpl
import mb00.android.codehub.ui.search.view.SearchActivity
import mb00.android.codehub.ui.search.viewmodel.SearchViewModel


@Module(includes = [SearchActivityFragmentsModule::class])
class SearchActivityModule {

    @Provides
    internal fun provideSearchInteractor(activity: SearchActivity, apiCallManager: ApiCallManager): SearchInteractor {
        return SearchInteractorImpl(activity, apiCallManager)
    }

    @Provides
    internal fun provideSearchRouter(activity: SearchActivity): SearchRouter {
        return SearchRouterImpl(activity)
    }

    @Provides
    internal fun provideSearchViewModel(interactor: SearchInteractor, router: SearchRouter): SearchViewModel {
        return SearchViewModel(interactor, router)
    }

}
