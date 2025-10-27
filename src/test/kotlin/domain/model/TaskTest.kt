package domain.model

import com.example.domain.model.Task
import com.example.domain.model.TaskId
import com.example.domain.model.TaskStatus
import com.example.shared.error.AppException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TaskTest : StringSpec() {
    init {
        "Create a task" {
            val task = Task(TaskId(1), "test")
            task.id!!.value shouldBe 1
            task.name shouldBe "test"
            task.status shouldBe TaskStatus.TODO
        }

        "Create a task with specific status" {
            val task = Task(TaskId(1), "test", TaskStatus.IN_PROGRESS)
            task.status shouldBe TaskStatus.IN_PROGRESS
        }

        "Create a task with DONE status" {
            val task = Task(TaskId(1), "test", TaskStatus.DONE)
            task.status shouldBe TaskStatus.DONE
        }

        "Throw an error if name is empty" {
            val exception = shouldThrow<AppException.Invalid> { Task(TaskId(1), "") }
            exception.message shouldBe "Task name must not be empty"
        }

        "Start a TODO task" {
            val task = Task(TaskId(1), "test")
            val startedTask = task.start()
            startedTask.status shouldBe TaskStatus.IN_PROGRESS
        }

        "Cannot start a task that is not in TODO status" {
            val task = Task(TaskId(1), "test", TaskStatus.IN_PROGRESS)
            val exception = shouldThrow<AppException.Invalid> { task.start() }
            exception.message shouldBe "Only TODO tasks can be started"
        }

        "Complete an IN_PROGRESS task" {
            val task = Task(TaskId(1), "test", TaskStatus.IN_PROGRESS)
            val completedTask = task.complete()
            completedTask.status shouldBe TaskStatus.DONE
        }

        "Cannot complete a task that is not IN_PROGRESS" {
            val task = Task(TaskId(1), "test", TaskStatus.TODO)
            val exception = shouldThrow<AppException.Invalid> { task.complete() }
            exception.message shouldBe "Only IN_PROGRESS tasks can be completed"
        }

        "Reset a non-TODO task" {
            val task = Task(TaskId(1), "test", TaskStatus.DONE)
            val resetTask = task.reset()
            resetTask.status shouldBe TaskStatus.TODO
        }

        "Cannot reset a task that is already in TODO status" {
            val task = Task(TaskId(1), "test")
            val exception = shouldThrow<AppException.Invalid> { task.reset() }
            exception.message shouldBe "Task is already in TODO status"
        }
    }
}
