package mb00.android.codehub.ui.home.inject

import android.support.v4.app.Fragment

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import mb00.android.codehub.ui.user.view.HomeFollowersFragment
import mb00.android.codehub.ui.user.view.HomeFollowingFragment
import mb00.android.codehub.ui.user.view.HomePulseFragment
import mb00.android.codehub.ui.user.view.HomeReposFragment


@Module
abstract class HomeFragmentProvider {

    @Binds
    @IntoMap
    @FragmentKey(HomeFollowersFragment::class)
    internal abstract fun bindHomeFollowersFragment(builder: HomeFollowersFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(HomeFollowingFragment::class)
    internal abstract fun bindHomeFollowingFragment(builder: HomeFollowingFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(HomePulseFragment::class)
    internal abstract fun bindHomePulseFragment(builder: HomePulseFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(HomeReposFragment::class)
    internal abstract fun bindHomeReposFragment(builder: HomeReposFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

}
