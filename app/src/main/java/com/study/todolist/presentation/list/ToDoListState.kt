package com.study.todolist.presentation.list

import com.study.todolist.data.entity.ToDoEntity

sealed class ToDoListState {

    object UnInitialized: ToDoListState()

    object Loading: ToDoListState()

    data class Suceess(
        val toDoList: List<ToDoEntity>
    ): ToDoListState()

    object Error: ToDoListState()

}
