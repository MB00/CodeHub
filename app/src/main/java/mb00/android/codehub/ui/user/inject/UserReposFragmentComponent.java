package mb00.android.codehub.ui.user.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.user.view.UserReposFragment;


@Subcomponent(modules = UserReposFragmentModule.class)
public interface UserReposFragmentComponent extends AndroidInjector<UserReposFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<UserReposFragment> {}

}