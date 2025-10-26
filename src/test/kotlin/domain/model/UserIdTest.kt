package domain.model

import com.example.domain.model.UserId
import com.example.shared.error.AppException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class UserIdTest : StringSpec() {
    init {
        "Create a user id" {
            val userId = UserId(1)
            userId.value shouldBe 1
        }

        "Throw an error if value is smaller than 0" {
            val exception = shouldThrow<AppException.Invalid> { UserId(0) }
            exception.message shouldBe "User id must be positive"
        }
    }
}
