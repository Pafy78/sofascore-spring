package com.baudoin.sofascore

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class SofascoreApplication {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(SofascoreApplication::class.java, *args)
        }
    }

}