package usecase

import com.example.domain.model.User
import com.example.domain.model.UserId
import com.example.domain.repository.IUserRepository
import com.example.usecase.UserUsecase
import com.example.usecase.shared.ITransactionManager
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
            every { mockRepository.findAll() } returns
                    listOf(
                            User(UserId(1), "Alice"),
                            User(UserId(2), "Bob"),
                    )

            // テスト対象を作成
            val mockTxManager = mockk<ITransactionManager>()
            every { mockTxManager.runInTransaction<List<User>>(any()) } answers
                    {
                        firstArg<() -> List<User>>().invoke()
                    }

            val userUsecase = UserUsecase(mockRepository, mockTxManager)
            val users = userUsecase.getAllUsers()

            users[0].id shouldBe 1
            users[0].name shouldBe "Alice"
            users[1].id shouldBe 2
            users[1].name shouldBe "Bob"
        }
    }
}
