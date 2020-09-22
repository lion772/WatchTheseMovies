package com.example.watchthesemoviestest.di

import com.example.watchthesemoviestest.BuildConfig
import com.example.watchthesemoviestest.moviecase.MovieUseCase
import com.example.watchthesemoviestest.network.AuthInterceptor
import com.example.watchthesemoviestest.network.MovieApi
import com.example.watchthesemoviestest.repository.MovieRepository
import com.example.watchthesemoviestest.viewmodel.MovieViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Module {
    val moduleDI = module {
        single { MovieRepository(context = get(), movieApi = get()) }
        single { MovieUseCase(movieRepository = get()) }
        viewModel { MovieViewModel(movieUseCase = get(), application = get()) }
    }

    val networkModule = module {
        factory { AuthInterceptor() }
        factory { provideOkHttpClient(get()) }
        factory { provideMovieApi(get()) }
        single { provideRetrofit(get()) }
    }

    private fun provideMovieApi(retrofit: Retrofit) = retrofit.create(MovieApi::class.java)

    private fun provideRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder().baseUrl(BuildConfig.HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    private fun provideOkHttpClient(authInterceptor: AuthInterceptor) =
        OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()

}