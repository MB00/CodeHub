package mb00.android.codehub.ui.repo.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.repo.view.RepoCodeFragment;


@Subcomponent(modules = RepoCodeFragmentModule.class)
public interface RepoCodeFragmentComponent extends AndroidInjector<RepoCodeFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RepoCodeFragment> {}

}
