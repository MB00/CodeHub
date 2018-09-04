package mb00.android.codehub.ui.user.inject;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import mb00.android.codehub.ui.user.view.UserActivity;


@Subcomponent(modules = {
        UserActivityModule.class,
        UserFragmentProvider.class
})
public interface UserActivityComponent extends AndroidInjector<UserActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<UserActivity> {}

}
