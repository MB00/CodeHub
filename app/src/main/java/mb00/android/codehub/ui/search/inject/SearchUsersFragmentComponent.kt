package mb00.android.codehub.ui.search.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.search.view.SearchUsersFragment


@Subcomponent(modules = [
    SearchUsersFragmentModule::class
])
interface SearchUsersFragmentComponent : AndroidInjector<SearchUsersFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SearchUsersFragment>()

}
