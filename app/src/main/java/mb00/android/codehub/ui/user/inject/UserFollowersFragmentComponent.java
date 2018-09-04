package mb00.android.codehub.ui.user.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.user.view.UserFollowersFragment;


@Subcomponent(modules = UserFollowersFragmentModule.class)
public interface UserFollowersFragmentComponent extends AndroidInjector<UserFollowersFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<UserFollowersFragment> {}

}
