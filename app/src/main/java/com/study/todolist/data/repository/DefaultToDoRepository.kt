package com.study.todolist.data.repository

import com.study.todolist.data.entity.ToDoEntity

class DefaultToDoRepository: ToDoRepository {

    override suspend fun getToDoList(): List<ToDoEntity> {
        TODO()
    }

    override suspend fun getToDoItem(id: Long): ToDoEntity{
        TODO()
    }

    override suspend fun insertToDoItem(toDoEntity: ToDoEntity): Long {
        TODO()
    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {
        TODO()
    }

    override suspend fun updateToDoItem(toDoEntity: ToDoEntity) {
        TODO()
    }

    override suspend fun deleteToDoItem(id: Long) {
        TODO()
    }

    override suspend fun deleteAll() {
        TODO()
    }

}
