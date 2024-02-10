import Factories.dataTestFactory
import Factories.textTestFactory
import data.InfoDto
import data.WrongDto
import io.kotest.core.spec.style.StringSpec

class BaseTest: StringSpec({

    val textSet = arrayOf("text", "wrong_text", "super wrong text")
    val testInfo = arrayOf(
        InfoDto("infoName", 1),
        WrongDto("rich info", 2, 200)
    )

    textSet.filter {
        !it.contains(" ")
    }.forEach {
        include(prefix = it ,textTestFactory(it))
    }

    testInfo.forEach {
        include(prefix = it.javaClass.toString(), dataTestFactory(it))
    }
})