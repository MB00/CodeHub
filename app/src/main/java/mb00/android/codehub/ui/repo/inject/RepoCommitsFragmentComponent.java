package mb00.android.codehub.ui.repo.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.repo.view.RepoCommitsFragment;


@Subcomponent(modules = RepoCommitsFragmentModule.class)
public interface RepoCommitsFragmentComponent extends AndroidInjector<RepoCommitsFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RepoCommitsFragment> {}

}
