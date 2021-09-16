package com.study.todolist.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.todolist.data.entity.ToDoEntity
import com.study.todolist.domain.todo.DeleteAllToDoItemUseCase
import com.study.todolist.domain.todo.GetToDoListUseCase
import com.study.todolist.domain.todo.UpdateToDoItemUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ListViewModel(
    private val getToDoListUseCase: GetToDoListUseCase,
    private val updateToDoItemUseCase: UpdateToDoItemUseCase,
    private val deleteAllToDoItemUseCase: DeleteAllToDoItemUseCase
): ViewModel() {
    private var _toDoListLiveData = MutableLiveData<ToDoListState>(ToDoListState.UnInitialized)
    val toDoListLiveData: LiveData<ToDoListState> = _toDoListLiveData

    fun fetchData(): Job = viewModelScope.launch {
        _toDoListLiveData.postValue(ToDoListState.Loading)
        _toDoListLiveData.postValue(ToDoListState.Suceess(getToDoListUseCase()))
    }

    fun updateEntity(toDoEntity: ToDoEntity) = viewModelScope.launch {
        updateToDoItemUseCase.invoke(toDoEntity)
    }

    fun deleteAll() = viewModelScope.launch {
        _toDoListLiveData.postValue(ToDoListState.Loading)
        deleteAllToDoItemUseCase.invoke()
        _toDoListLiveData.postValue(ToDoListState.Suceess(listOf()))
    }
}
