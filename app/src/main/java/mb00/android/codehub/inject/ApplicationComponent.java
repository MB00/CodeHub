package mb00.android.codehub.inject;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import mb00.android.codehub.MobileApplication;


@Singleton
@Component(modules = {
        ApplicationModule.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBuilderModule.class
})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }

    void inject(MobileApplication mobileApplication);
}
