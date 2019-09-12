package mb00.android.codehub.inject

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import mb00.android.codehub.api.manager.ApiCallManager
import javax.inject.Singleton


@Module(includes = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AndroidActivitiesModule::class

])
open class ApplicationModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideApiCallManager(context: Context): ApiCallManager {
        return ApiCallManager(context)
    }

}
