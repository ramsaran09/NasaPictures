package dev.muthuram.nasapictures.di

import dev.muthuram.nasapictures.manager.AssetsManager
import dev.muthuram.nasapictures.repository.SpaceInfoRepository
import dev.muthuram.nasapictures.ui.home.HomeViewModel
import dev.muthuram.nasapictures.ui.image_details.SpaceImageDetailsViewModel
import dev.muthuram.nasapictures.ui.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    private val viewModelModules = module {
        viewModel { HomeViewModel(get()) }
        viewModel { SpaceImageDetailsViewModel() }
        viewModel { SplashViewModel() }
    }

    private val repoModules = module {
        single { SpaceInfoRepository(get()) }
    }

    private val commonModules = module {
        single { AssetsManager(androidContext())}
    }

    fun appModules() = viewModelModules + repoModules + commonModules
}