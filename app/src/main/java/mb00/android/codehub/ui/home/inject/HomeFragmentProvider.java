package mb00.android.codehub.ui.home.inject;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import mb00.android.codehub.ui.user.view.HomeFollowersFragment;
import mb00.android.codehub.ui.user.view.HomeFollowingFragment;
import mb00.android.codehub.ui.user.view.HomePulseFragment;
import mb00.android.codehub.ui.user.view.HomeReposFragment;

@Module
public abstract class HomeFragmentProvider {

    @Binds
    @IntoMap
    @FragmentKey(HomeFollowersFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindHomeFollowersFragment(HomeFollowersFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(HomeFollowingFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindHomeFollowingFragment(HomeFollowingFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(HomePulseFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindHomePulseFragment(HomePulseFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(HomeReposFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindHomeReposFragment(HomeReposFragmentComponent.Builder builder);


}
