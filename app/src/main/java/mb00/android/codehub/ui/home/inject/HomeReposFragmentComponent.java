package mb00.android.codehub.ui.home.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.user.view.HomeReposFragment;


@Subcomponent(modules = HomePulseFragmentModule.class)
public interface HomeReposFragmentComponent extends AndroidInjector<HomeReposFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HomeReposFragment> {}

}
