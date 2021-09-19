package com.microservices.reactive

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DatabaseInitializer {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var mongoOperations: ReactiveMongoOperations

    companion object {
        val initialCustomers = listOf(Customer(1, "Chris"),
            Customer(2, "Donna"),
            Customer(3, "John", Customer.Telephone("+44", "444444444")))

    }

    @PostConstruct
    fun initData() {
        customerRepository.saveAll(initialCustomers).subscribe {
            println("Default customers created")
        }
    }
}