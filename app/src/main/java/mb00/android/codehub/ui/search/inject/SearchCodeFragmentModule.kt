package mb00.android.codehub.ui.search.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.search.interactor.SearchInteractor
import mb00.android.codehub.ui.search.router.SearchRouter
import mb00.android.codehub.ui.search.viewmodel.SearchCodeViewModel


@Module
class SearchCodeFragmentModule {

    @Provides
    internal fun provideSearchCodeViewModel(interactor: SearchInteractor, router: SearchRouter): SearchCodeViewModel {
        return SearchCodeViewModel(interactor, router)
    }

}
