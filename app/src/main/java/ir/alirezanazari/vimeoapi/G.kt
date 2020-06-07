package ir.alirezanazari.vimeoapi

import android.app.Application
import ir.alirezanazari.vimeoapi.internal.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class G : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@G)
            modules(appModules)
        }
    }
}