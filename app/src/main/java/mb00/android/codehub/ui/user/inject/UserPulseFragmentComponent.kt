package mb00.android.codehub.ui.user.inject

import dagger.Subcomponent
import dagger.android.AndroidInjector
import mb00.android.codehub.ui.user.view.UserPulseFragment


@Subcomponent(modules = [
    UserPulseFragmentModule::class
])
interface UserPulseFragmentComponent : AndroidInjector<UserPulseFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<UserPulseFragment>()

}
