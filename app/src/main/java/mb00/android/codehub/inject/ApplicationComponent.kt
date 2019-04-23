package mb00.android.codehub.inject

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import mb00.android.codehub.MobileApplication
import javax.inject.Singleton


@Singleton
@Component(modules = [
    ApplicationModule::class,
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(mobileApplication: MobileApplication)
}
