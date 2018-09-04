package mb00.android.codehub.ui.gist.inject;

import dagger.Module;
import dagger.Provides;
import mb00.android.codehub.ui.gist.viewmodel.GistViewModel;


@Module
public class GistActivityModule {

    @Provides
    GistViewModel provideGistViewModel() {
        return new GistViewModel();
    }

}
