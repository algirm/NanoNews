package com.noob.nanonews.di

import android.content.Context
import android.content.SharedPreferences
import com.noob.nanonews.util.DispatcherProvider
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences =
        context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider {
        return object : DispatcherProvider {
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO
            override val default: CoroutineDispatcher
                get() = Dispatchers.Default
            override val unconfined: CoroutineDispatcher
                get() = Dispatchers.Unconfined
        }
    }

    @Singleton
    @Provides
    fun providePicasso(): Picasso = Picasso.get()

}