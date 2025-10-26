package domain.model

import com.example.domain.model.ItemId
import com.example.shared.error.AppException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ItemIdTest : StringSpec() {
    init {
        "Create an item id" {
            val itemId = ItemId(1)
            itemId.value shouldBe 1
        }

        "Throw an error if value is smaller than 0" {
            val exception = shouldThrow<AppException.Invalid> { ItemId(0) }
            exception.message shouldBe "Item id must be positive"
        }
    }
}
