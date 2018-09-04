package mb00.android.codehub.ui.user.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.user.view.UserGistsFragment;


@Subcomponent(modules = UserGistsFragmentModule.class)
public interface UserGistsFragmentComponent extends AndroidInjector<UserGistsFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<UserGistsFragment> {}

}
