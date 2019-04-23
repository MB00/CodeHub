package mb00.android.codehub.ui.search.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.search.view.SearchActivity


@Subcomponent(modules = [
    SearchActivityModule::class,
    SearchFragmentProvider::class
])
interface SearchActivityComponent : AndroidInjector<SearchActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SearchActivity>()

}
