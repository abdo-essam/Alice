package com.ae.alice.data.di

import org.koin.dsl.module
import com.ae.alice.data.fake.FakeBrandRepository
import com.ae.alice.data.fake.FakeCarModelRepository
import com.ae.alice.domain.repository.BrandRepository
import com.ae.alice.domain.repository.CarModelRepository

/**
 * Koin module for data layer dependencies.
 * Uses fake repositories for development.
 */
val dataModule = module {
    single<BrandRepository> { FakeBrandRepository() }
    single<CarModelRepository> { FakeCarModelRepository() }
}
