package com.noteamname.androidhackathon

import android.app.Application
import com.noteamname.androidhackathon.toiletRoll.viewModels.ToiletRollViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class LibApplication : Application() {
    private val koinModule = module {
        viewModel { ToiletRollViewModel() }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@LibApplication)
            modules(koinModule)
        }
    }
}