package com.example.gametime.di

import android.content.Context
import com.example.gametime.data.repositoryImpl.SharedPrefsRepositoryImpl
import com.example.gametime.domain.repository.SharedPrefsRepository
import com.example.gametime.domain.usecase.ClearCurrentSessionUseCase
import com.example.gametime.domain.usecase.IsEmailValidUseCase
import com.example.gametime.domain.usecase.LoadCurrentUserUseCase
import com.example.gametime.domain.usecase.LoadOnBoardStatusUseCase
import com.example.gametime.domain.usecase.SaveCurrentUserUseCase
import com.example.gametime.domain.usecase.SaveOnBoardStatusUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

//25.03.2026
//Алексей
//класс для инъектирования зависимостей
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideSharedPrefsRepository(@ApplicationContext context: Context): SharedPrefsRepository {
        return SharedPrefsRepositoryImpl(context)
    }

    @Provides
    fun provideLoadCurrentUserUseCase(sharedPrefsRepository: SharedPrefsRepository): LoadCurrentUserUseCase {
        return LoadCurrentUserUseCase(sharedPrefsRepository)
    }

    @Provides
    fun provideLoadOnBoardStatusUseCase(sharedPrefsRepository: SharedPrefsRepository): LoadOnBoardStatusUseCase{
        return LoadOnBoardStatusUseCase(sharedPrefsRepository)
    }

    @Provides
    fun provideSaveCurrentUserUseCase(sharedPrefsRepository: SharedPrefsRepository): SaveCurrentUserUseCase{
        return SaveCurrentUserUseCase(sharedPrefsRepository)
    }

    @Provides
    fun provideSaveOnBoardStatusUseCase(sharedPrefsRepository: SharedPrefsRepository): SaveOnBoardStatusUseCase{
        return SaveOnBoardStatusUseCase(sharedPrefsRepository)
    }

    @Provides
    fun provideIsEmailValidUseCase(): IsEmailValidUseCase{
        return IsEmailValidUseCase()
    }

    @Provides
    fun provideClearCurrentSessionUseCase(sharedPrefsRepository: SharedPrefsRepository): ClearCurrentSessionUseCase{
        return ClearCurrentSessionUseCase(sharedPrefsRepository)
    }
}