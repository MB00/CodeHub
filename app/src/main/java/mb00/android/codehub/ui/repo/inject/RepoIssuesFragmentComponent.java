package mb00.android.codehub.ui.repo.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.repo.view.RepoIssuesFragment;


@Subcomponent(modules = RepoIssuesFragmentModule.class)
public interface RepoIssuesFragmentComponent extends AndroidInjector<RepoIssuesFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RepoIssuesFragment> {}

}
