package ir.alirezanazari.vimeoapi.internal

import ir.alirezanazari.vimeoapi.data.net.ApiConfig
import ir.alirezanazari.vimeoapi.data.net.NetworkDataManager
import ir.alirezanazari.vimeoapi.data.net.NetworkDataManagerImpl
import ir.alirezanazari.vimeoapi.data.repository.VideoRepository
import ir.alirezanazari.vimeoapi.data.repository.VideoRepositoryImpl
import ir.alirezanazari.vimeoapi.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModules = module {

    single { ApiConfig.invoke() }
    single<NetworkDataManager> { NetworkDataManagerImpl(get()) }

    factory<VideoRepository> { VideoRepositoryImpl(get()) }

    viewModel { SearchViewModel(get()) }
}