package com.study.todolist.domain.todo

import com.study.todolist.data.entity.ToDoEntity
import com.study.todolist.data.repository.ToDoRepository
import com.study.todolist.domain.UseCase

internal class DeleteToDoItemUseCase(
    private val toDoRepository: ToDoRepository
): UseCase {

    suspend operator fun invoke(id : Long) {
        return toDoRepository.deleteToDoItem(id)
    }

}
