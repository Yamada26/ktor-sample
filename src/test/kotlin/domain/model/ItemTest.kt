package domain.model

import com.example.domain.model.Item
import com.example.shared.error.AppException
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.data.headers
import io.kotest.data.table

class ItemTest : StringSpec() {
    init {
        "Create an item" {
            val item = Item(1, "test")
            item.id shouldBe 1
            item.name shouldBe "test"
        }

        "Throw an error if name is empty" {
            val exception = shouldThrow<AppException.Invalid> {
                Item(1, "")
            }
            exception.message shouldBe "Item name must not be empty"
        }
    }
}