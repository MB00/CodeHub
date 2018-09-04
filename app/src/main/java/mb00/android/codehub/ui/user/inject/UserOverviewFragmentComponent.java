package mb00.android.codehub.ui.user.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.user.view.UserOverviewFragment;


@Subcomponent(modules = UserOverviewFragmentModule.class)
public interface UserOverviewFragmentComponent extends AndroidInjector<UserOverviewFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<UserOverviewFragment> {}

}
