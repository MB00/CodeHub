package mb00.android.codehub.ui.gist.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.gist.view.GistActivity;


@Subcomponent(modules = GistActivityModule.class)
public interface GistActivityComponent extends AndroidInjector<GistActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<GistActivity> {}

}
