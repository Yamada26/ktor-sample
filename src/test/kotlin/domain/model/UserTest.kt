package domain.model

import com.example.domain.model.User
import com.example.domain.model.UserId
import com.example.shared.error.AppException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class UserTest : StringSpec() {
    init {
        "Create a user" {
            val user = User(UserId(1), "test")
            user.id.value shouldBe 1
            user.name shouldBe "test"
        }

        "Throw an error if name is empty" {
            val exception = shouldThrow<AppException.Invalid> { User(UserId(1), "") }
            exception.message shouldBe "User name must not be empty"
        }
    }
}
