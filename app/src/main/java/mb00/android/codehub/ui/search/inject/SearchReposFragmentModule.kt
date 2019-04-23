package mb00.android.codehub.ui.search.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.search.interactor.SearchInteractor
import mb00.android.codehub.ui.search.router.SearchRouter
import mb00.android.codehub.ui.search.viewmodel.SearchReposViewModel


@Module
class SearchReposFragmentModule {

    @Provides
    internal fun provideSearchReposViewModel(interactor: SearchInteractor, router: SearchRouter): SearchReposViewModel {
        return SearchReposViewModel(interactor, router)
    }

}
