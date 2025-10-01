package com.rp.chalisapath.di

import android.content.Context
import com.rp.chalisapath.data.local.ChalisaDataSource
import com.rp.chalisapath.data.local.ChalisaJsonParser
import com.rp.chalisapath.data.repository.ChalishaRepositoryImp
import com.rp.chalisapath.domain.repository.ChalishaRepository
import com.rp.chalisapath.domain.usecase.GetChalishaByIdUseCase
import com.rp.chalisapath.domain.usecase.GetChalishaInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideChalisaSource(@ApplicationContext context: Context): ChalisaDataSource {
        return ChalisaJsonParser(context)
    }

    @Provides
    @Singleton
    fun provideChalishaRepository(
        chalisaDataSource: ChalisaDataSource
    ): ChalishaRepository {
        return ChalishaRepositoryImp(chalisaDataSource)
    }

    @Provides
    @Singleton
    fun provideGetChalishaInfosUseCase(
        repository: ChalishaRepository
    ): GetChalishaInfoUseCase {
        return GetChalishaInfoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetChalishaByIdUseCase(
        repository: ChalishaRepository
    ): GetChalishaByIdUseCase {
        return GetChalishaByIdUseCase(repository)
    }
}
