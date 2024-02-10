package Factories

import configs.TestConfig
import io.kotest.assertions.asClue
import io.kotest.core.Tag
import io.kotest.core.spec.style.stringSpec
import io.kotest.core.test.TestCaseSeverityLevel
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.string.shouldHaveLength

fun datatestFactory(data: String) = stringSpec {
    tags(TestConfig().textTag)

    val testData = data
    val targetLength = 4
    val targetText = "text"

    "Данные нужной длины".config(enabled = true, severity = TestCaseSeverityLevel.BLOCKER) {
        { "Длина должна быть равна $targetLength" }.asClue {
            testData.shouldHaveLength(targetLength)
        }
    }

    "Содержание данных корректно".config(enabled = true, severity = TestCaseSeverityLevel.MINOR) {
        { "Содержание должно быть $targetText" }.asClue {
            testData.shouldBeEqual(targetText)
        }
    }

}