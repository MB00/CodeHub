package mb00.android.codehub.ui.search.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.search.view.SearchIssuesFragment


@Subcomponent(modules = [
    SearchIssuesFragmentModule::class
])
interface SearchIssuesFragmentComponent : AndroidInjector<SearchIssuesFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SearchIssuesFragment>()

}
