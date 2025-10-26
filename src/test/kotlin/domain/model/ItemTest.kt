package domain.model

import com.example.domain.model.Item
import com.example.domain.model.ItemId
import com.example.shared.error.AppException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ItemTest : StringSpec() {
    init {
        "Create an item" {
            val item = Item(ItemId(1), "test")
            item.id.value shouldBe 1
            item.name shouldBe "test"
        }

        "Throw an error if name is empty" {
            val exception = shouldThrow<AppException.Invalid> { Item(ItemId(1), "") }
            exception.message shouldBe "Item name must not be empty"
        }
    }
}
