package br.com.til

import io.micronaut.runtime.Micronaut.build


fun main(args: Array<String>) {

	build()
	    .args(*args)
		.packages("br.com.til")
		.start()

}

