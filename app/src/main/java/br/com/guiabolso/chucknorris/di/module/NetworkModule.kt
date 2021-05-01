package br.com.guiabolso.chucknorris.di.module

import android.app.Application
import androidx.annotation.Nullable
import br.com.guiabolso.chucknorris.data.remote.ChuckNorrisService
import br.com.guiabolso.chucknorris.data.remote.RemoteDataSource
import br.com.guiabolso.chucknorris.utils.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(private val application: Application) {
    @Provides
    @Reusable
    internal fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC

        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        // 15 MiB cache
        val cache = Cache(cacheDir, 15 * 1024 * 1024)
        return OkHttpClient.Builder()
            .cache(
                cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build() }

    @Reusable
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit? {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Reusable
    @Provides
    fun provideChuckNorrisService(@Nullable retrofit: Retrofit): ChuckNorrisService =
        retrofit.create(ChuckNorrisService::class.java)

    @Reusable
    @Provides
    fun provideRemoteDataSource(service: ChuckNorrisService): RemoteDataSource =
        RemoteDataSource(service)
}