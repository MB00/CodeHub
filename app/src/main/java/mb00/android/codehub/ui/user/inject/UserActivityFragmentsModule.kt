package mb00.android.codehub.ui.user.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mb00.android.codehub.ui.user.view.*


@Module
abstract class UserActivityFragmentsModule {

    @ContributesAndroidInjector(modules = [UserFollowersFragmentModule::class])
    internal abstract fun bindUserFollowersFragment(): UserFollowersFragment

    @ContributesAndroidInjector(modules = [UserFollowingFragmentModule::class])
    internal abstract fun bindUserFollowingFragment(): UserFollowingFragment

    @ContributesAndroidInjector(modules = [UserGistsFragmentModule::class])
    internal abstract fun bindUserGistsFragment(): UserGistsFragment

    @ContributesAndroidInjector(modules = [UserOverviewFragmentModule::class])
    internal abstract fun bindUserOverviewFragment(): UserOverviewFragment

    @ContributesAndroidInjector(modules = [UserPulseFragmentModule::class])
    internal abstract fun bindUserPulseFragment(): UserPulseFragment

    @ContributesAndroidInjector(modules = [UserReposFragmentModule::class])
    internal abstract fun bindUserReposFragment(): UserReposFragment

    @ContributesAndroidInjector(modules = [UserStarredFragmentModule::class])
    internal abstract fun bindUserStarredFragment(): UserStarredFragment

}
