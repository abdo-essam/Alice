package com.ae.alice.data.di

import org.koin.dsl.module
import com.ae.alice.data.fake.FakeBrandRepository
import com.ae.alice.data.fake.FakeCarModelRepository
import com.ae.alice.data.fake.FakePlaceRepository
import com.ae.alice.domain.repository.BrandRepository
import com.ae.alice.domain.repository.CarModelRepository
import com.ae.alice.domain.repository.PlaceRepository
import com.ae.alice.domain.repository.AppPreferencesRepository
import com.ae.alice.domain.repository.AuthRepository
import com.ae.alice.data.AppPreferencesRepositoryImpl
import com.ae.alice.data.FirebaseAuthRepository
import com.russhwolf.settings.Settings

val dataModule = module {
    single<Settings> { Settings() }
    single<AppPreferencesRepository> { AppPreferencesRepositoryImpl(get(), get()) }
    single<BrandRepository> { FakeBrandRepository() }
    single<CarModelRepository> { FakeCarModelRepository() }
    single<PlaceRepository> { FakePlaceRepository() }
    single<AuthRepository> { FirebaseAuthRepository() }
}
