package ir.alirezanazari.vimeoapi.internal

import ir.alirezanazari.vimeoapi.data.net.ApiConfig
import ir.alirezanazari.vimeoapi.data.net.NetworkDataManager
import ir.alirezanazari.vimeoapi.data.net.NetworkDataManagerImpl
import ir.alirezanazari.vimeoapi.data.net.TokenInterceptor
import ir.alirezanazari.vimeoapi.data.repository.VideoRepository
import ir.alirezanazari.vimeoapi.data.repository.VideoRepositoryImpl
import ir.alirezanazari.vimeoapi.ui.search.SearchResultAdapter
import ir.alirezanazari.vimeoapi.ui.search.SearchViewModel
import ir.alirezanazari.vimeoapi.ui.video.VideoCommentsAdapter
import ir.alirezanazari.vimeoapi.ui.video.VideoPicturesAdapter
import ir.alirezanazari.vimeoapi.ui.video.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModules = module {

    single { ImageLoader() }
    single { TokenInterceptor() }
    single { ApiConfig.invoke(get()) }
    single<NetworkDataManager> { NetworkDataManagerImpl(get()) }

    factory<VideoRepository> { VideoRepositoryImpl(get()) }
    factory { SearchResultAdapter(get()) }
    factory { VideoPicturesAdapter(get()) }
    factory { VideoCommentsAdapter() }

    viewModel { SearchViewModel(get()) }
    viewModel { VideoViewModel(get()) }
}