package mb00.android.codehub.ui.main.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.main.view.MainActivity;


@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {}

}
