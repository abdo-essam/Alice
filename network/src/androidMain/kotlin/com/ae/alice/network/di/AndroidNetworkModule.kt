package com.ae.alice.network.di

import com.ae.alice.network.client.KtorClientFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

val androidNetworkModule = module {
    single<HttpClient> {
        KtorClientFactory.create(
            engine = OkHttp.create(),
            baseUrl = "https://private-anon-dc7b949987-carsapi1.apiary-mock.com/" // Using a mock API for now or whatever URL is used
        )
    }
}
