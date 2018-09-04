package mb00.android.codehub.ui.gist.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.gist.view.GistCommentsFragment;


@Subcomponent(modules = GistCommentsFragmentModule.class)
public interface GistCommentsFragmentComponent extends AndroidInjector<GistCommentsFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<GistCommentsFragment> {}

}
