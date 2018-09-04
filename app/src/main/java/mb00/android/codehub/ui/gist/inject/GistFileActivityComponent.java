package mb00.android.codehub.ui.gist.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.about.view.AboutActivity;
import mb00.android.codehub.ui.gist.view.GistFileActivity;


@Subcomponent(modules = GistFileActivityModule.class)
public interface GistFileActivityComponent extends AndroidInjector<GistFileActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<GistFileActivity> {}

}
