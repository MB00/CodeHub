package mb00.android.codehub.ui.settings.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.settings.view.SettingsActivity;


@Subcomponent(modules = SettingsActivityModule.class)
public interface SettingsActivityComponent extends AndroidInjector<SettingsActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SettingsActivity> {}

}
