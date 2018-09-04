package mb00.android.codehub.ui.home.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.home.view.HomeActivity;


@Subcomponent(modules = {
        HomeActivityModule.class,
        HomeFragmentProvider.class
})
public interface HomeActivityComponent extends AndroidInjector<HomeActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HomeActivity> {}

}
