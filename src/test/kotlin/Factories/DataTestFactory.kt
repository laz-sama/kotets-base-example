package Factories

import data.InfoDto
import io.kotest.core.spec.style.stringSpec
import io.kotest.core.test.EnabledIf
import io.kotest.core.test.EnabledOrReasonIf
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.kotest.matchers.types.shouldBeTypeOf

fun dataTestFactory(testData: Any) = stringSpec {

    var basicSuccess = false

    "Проверим структуру данных".config(blockingTest = true) {
        testData.javaClass.shouldBe(InfoDto::class.java)
        basicSuccess = true
    }

    val otherEnabled: EnabledIf = { basicSuccess }

    "Проверим содержание".config(enabledIf = otherEnabled) {
        val info: InfoDto = testData as InfoDto

        info.name.shouldBeEqual("infoName")
    }
}