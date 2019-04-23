package mb00.android.codehub.ui.user.inject

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import mb00.android.codehub.ui.user.view.*


@Module
abstract class UserFragmentProvider {

    @Binds
    @IntoMap
    @FragmentKey(UserFollowersFragment::class)
    internal abstract fun bindUserFollowersFragment(builder: UserFollowersFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(UserFollowingFragment::class)
    internal abstract fun bindUserFollowingFragment(builder: UserFollowingFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(UserGistsFragment::class)
    internal abstract fun bindUserGistsFragment(builder: UserGistsFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(UserOverviewFragment::class)
    internal abstract fun bindUserOverviewFragment(builder: UserOverviewFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(UserPulseFragment::class)
    internal abstract fun bindUserPulseFragment(builder: UserPulseFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(UserReposFragment::class)
    internal abstract fun bindUserReposFragment(builder: UserReposFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(UserStarredFragment::class)
    internal abstract fun bindUserStarredFragment(builder: UserStarredFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

}
