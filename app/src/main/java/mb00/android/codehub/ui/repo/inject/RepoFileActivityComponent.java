package mb00.android.codehub.ui.repo.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.repo.view.RepoFileActivity;


@Subcomponent(modules = RepoFileActivityModule.class)
public interface RepoFileActivityComponent extends AndroidInjector<RepoFileActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RepoFileActivity> {}

}
