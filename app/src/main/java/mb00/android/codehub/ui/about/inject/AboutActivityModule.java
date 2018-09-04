package mb00.android.codehub.ui.about.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.about.viewmodel.AboutViewModel;


@Module
public class AboutActivityModule {

    @Provides
    AboutViewModel provideAboutViewModel() {
        return new AboutViewModel();
    }

}
