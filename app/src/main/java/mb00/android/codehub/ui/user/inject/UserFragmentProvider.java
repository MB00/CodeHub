package mb00.android.codehub.ui.user.inject;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import mb00.android.codehub.ui.user.view.UserFollowersFragment;
import mb00.android.codehub.ui.user.view.UserFollowingFragment;
import mb00.android.codehub.ui.user.view.UserGistsFragment;
import mb00.android.codehub.ui.user.view.UserOverviewFragment;
import mb00.android.codehub.ui.user.view.UserPulseFragment;
import mb00.android.codehub.ui.user.view.UserReposFragment;
import mb00.android.codehub.ui.user.view.UserStarredFragment;

@Module
public abstract class UserFragmentProvider {

    @Binds
    @IntoMap
    @FragmentKey(UserFollowersFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserFollowersFragment(UserFollowersFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UserFollowingFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserFollowingFragment(UserFollowingFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UserGistsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserGistsFragment(UserGistsFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UserOverviewFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserOverviewFragment(UserOverviewFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UserPulseFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserPulseFragment(UserPulseFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UserReposFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserReposFragment(UserReposFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(UserStarredFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindUserStarredFragment(UserStarredFragmentComponent.Builder builder);

}
