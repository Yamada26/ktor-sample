package domain.model

import com.example.domain.model.Item
import com.example.domain.model.User
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.data.headers
import io.kotest.data.table

class UserTest : StringSpec() {
    init {
        "Create a user" {
            val user = User(1, "test")
            user.id shouldBe 1
            user.name shouldBe "test"
        }

        "Throw an error if name is empty" {
            val exception = shouldThrow<IllegalArgumentException> {
                User(1, "")
            }
            exception.message shouldBe "User name must not be empty"
        }
    }
}