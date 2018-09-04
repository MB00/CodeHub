package mb00.android.codehub.ui.settings.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.settings.viewmodel.SettingsViewModel;


@Module
public class SettingsActivityModule {

    @Provides
    SettingsViewModel provideSettingsViewModel() {
        return new SettingsViewModel();
    }

}
