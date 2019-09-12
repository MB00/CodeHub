package mb00.android.codehub.ui.home.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mb00.android.codehub.ui.home.view.HomeFollowersFragment
import mb00.android.codehub.ui.home.view.HomeFollowingFragment
import mb00.android.codehub.ui.home.view.HomePulseFragment
import mb00.android.codehub.ui.home.view.HomeReposFragment


@Module
abstract class HomeActivityFragmentsModule {

    @ContributesAndroidInjector(modules = [HomeFollowersFragmentModule::class])
    internal abstract fun bindHomeFollowersFragment(): HomeFollowersFragment

    @ContributesAndroidInjector(modules = [HomeFollowingFragmentModule::class])
    internal abstract fun bindHomeFollowingFragment(): HomeFollowingFragment

    @ContributesAndroidInjector(modules = [HomePulseFragmentModule::class])
    internal abstract fun bindHomePulseFragment(): HomePulseFragment

    @ContributesAndroidInjector(modules = [HomeReposFragmentModule::class])
    internal abstract fun bindHomeReposFragment(): HomeReposFragment

}
