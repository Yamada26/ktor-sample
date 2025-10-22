package usecase

import com.example.domain.model.Item
import com.example.domain.model.ItemId
import com.example.domain.repository.IItemRepository
import com.example.usecase.ItemUsecase
import com.example.usecase.shared.ITransactionManager
import com.example.infrastructure.exposed.shared.ExposedTransactionManager
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ItemUsecaseTest : StringSpec() {
    init {
        "getAllItems: Success" {
            // モックの作成
            val mockRepository = mockk<IItemRepository>()

            // モックの振る舞いを定義
            every { mockRepository.findAll() } returns
                listOf(
                    Item(ItemId(1), "Apple"),
                    Item(ItemId(2), "Banana"),
                )

            // テスト対象を作成
            val mockTxManager = mockk<ITransactionManager>()
            every { 
                mockTxManager.runInTransaction<List<Item>>(any()) 
            } answers { 
                firstArg<() -> List<Item>>().invoke()
            }
            
            val itemUsecase = ItemUsecase(mockRepository, mockTxManager)
            val items = itemUsecase.getAllItems()

            items[0].id shouldBe 1
            items[0].name shouldBe "Apple"
            items[1].id shouldBe 2
            items[1].name shouldBe "Banana"
        }
    }
}
