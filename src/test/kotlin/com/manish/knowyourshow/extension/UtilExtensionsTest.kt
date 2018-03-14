package com.manish.knowyourshow.extension

import com.manish.knowyourshow.app.extensions.getLogger
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class UtilExtensionsTest {

    @Test
    fun getLoggerTest() {
        val logger = getLogger<UtilExtensionsTest>()
        Assertions.assertNotNull(logger)
    }

}