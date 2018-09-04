package mb00.android.codehub.ui.home.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.user.view.HomePulseFragment;


@Subcomponent(modules = HomePulseFragmentModule.class)
public interface HomePulseFragmentComponent extends AndroidInjector<HomePulseFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HomePulseFragment> {}

}
