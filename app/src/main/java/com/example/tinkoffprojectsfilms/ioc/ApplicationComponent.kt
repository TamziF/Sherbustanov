package com.example.tinkoffprojectsfilms.ioc

import com.example.tinkoffprojectsfilms.data.network.Network
import com.example.tinkoffprojectsfilms.data.repository.NetworkRepository

class ApplicationComponent {
    private val network: Network = Network()
    private val networkRepository: NetworkRepository = NetworkRepository(network.api)

    val viewModelFactory: ViewModelFactory = ViewModelFactory(networkRepository)
}