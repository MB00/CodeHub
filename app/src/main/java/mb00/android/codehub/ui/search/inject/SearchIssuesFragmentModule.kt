package mb00.android.codehub.ui.search.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.search.interactor.SearchInteractor
import mb00.android.codehub.ui.search.router.SearchRouter
import mb00.android.codehub.ui.search.viewmodel.SearchIssuesViewModel


@Module
class SearchIssuesFragmentModule {

    @Provides
    internal fun provideSearchIssuesViewModel(interactor: SearchInteractor, router: SearchRouter): SearchIssuesViewModel {
        return SearchIssuesViewModel(interactor, router)
    }

}
