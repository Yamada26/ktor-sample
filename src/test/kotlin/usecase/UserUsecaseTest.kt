package usecase

import com.example.domain.model.User
import com.example.domain.repository.IUserRepository
import com.example.usecase.UserUsecase
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class UserUsecaseTest : StringSpec() {
    init {
        "getAllUsers: Success" {
            // モックの作成
            val mockRepository = mockk<IUserRepository>()

            // モックの振る舞いを定義
            every { mockRepository.findAll() } returns listOf(
                User(1, "Alice"),
                User(2, "Bob")
            )

            // テスト対象を作成
            val userUsecase = UserUsecase(mockRepository)
            val users = userUsecase.getAllUsers()

            users[0].id shouldBe 1
            users[0].name shouldBe "Alice"
            users[1].id shouldBe 2
            users[1].name shouldBe "Bob"
        }
    }
}