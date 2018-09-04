package mb00.android.codehub.ui.repo.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.repo.view.RepoContributorsFragment;


@Subcomponent(modules = RepoContributorsFragmentModule.class)
public interface RepoContributorsFragmentComponent extends AndroidInjector<RepoContributorsFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RepoContributorsFragment> {}

}
