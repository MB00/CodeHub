package mb00.android.codehub.ui.login.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.login.view.LoginActivity;


@Subcomponent(modules = LoginActivityModule.class)
public interface LoginActivityComponent extends AndroidInjector<LoginActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LoginActivity> {}

}
