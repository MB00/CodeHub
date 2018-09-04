package mb00.android.codehub.ui.repo.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.repo.view.RepoReleasesFragment;


@Subcomponent(modules = RepoReleasesFragmentModule.class)
public interface RepoReleasesFragmentComponent extends AndroidInjector<RepoReleasesFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RepoReleasesFragment> {}

}
