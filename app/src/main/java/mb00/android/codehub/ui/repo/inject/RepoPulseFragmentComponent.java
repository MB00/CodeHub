package mb00.android.codehub.ui.repo.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.repo.view.RepoPulseFragment;


@Subcomponent(modules = RepoPulseFragmentModule.class)
public interface RepoPulseFragmentComponent extends AndroidInjector<RepoPulseFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RepoPulseFragment> {}

}
