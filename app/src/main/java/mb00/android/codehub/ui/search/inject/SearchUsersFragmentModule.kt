package mb00.android.codehub.ui.search.inject

import dagger.Module
import dagger.Provides
import mb00.android.codehub.ui.search.interactor.SearchInteractor
import mb00.android.codehub.ui.search.router.SearchRouter
import mb00.android.codehub.ui.search.viewmodel.SearchUsersViewModel


@Module
class SearchUsersFragmentModule {

    @Provides
    internal fun provideSearchUsersViewModel(interactor: SearchInteractor, router: SearchRouter): SearchUsersViewModel {
        return SearchUsersViewModel(interactor, router)
    }

}
