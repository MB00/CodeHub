package mb00.android.codehub.ui.user.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.user.view.UserFollowingFragment;


@Subcomponent(modules = UserFollowingFragmentModule.class)
public interface UserFollowingFragmentComponent extends AndroidInjector<UserFollowingFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<UserFollowingFragment> {}

}
