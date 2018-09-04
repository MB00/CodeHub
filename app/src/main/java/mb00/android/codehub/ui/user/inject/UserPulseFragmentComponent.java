package mb00.android.codehub.ui.user.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.user.view.UserPulseFragment;


@Subcomponent(modules = UserPulseFragmentModule.class)
public interface UserPulseFragmentComponent extends AndroidInjector<UserPulseFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<UserPulseFragment> {}

}
