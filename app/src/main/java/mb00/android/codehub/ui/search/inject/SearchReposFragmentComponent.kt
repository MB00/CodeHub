package mb00.android.codehub.ui.search.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.search.view.SearchReposFragment


@Subcomponent(modules = [
    SearchReposFragmentModule::class
])
interface SearchReposFragmentComponent : AndroidInjector<SearchReposFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SearchReposFragment>()

}
