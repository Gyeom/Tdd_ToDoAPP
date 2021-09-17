package com.study.todolist.di

import com.study.todolist.domain.todo.*
import com.study.todolist.domain.todo.DeleteAllToDoItemUseCase
import com.study.todolist.domain.todo.GetToDoListUseCase
import com.study.todolist.domain.todo.InsertToDoListUseCase
import com.study.todolist.domain.todo.InsertToDoItemUseCase
import com.study.todolist.domain.todo.UpdateToDoItemUseCase
import com.study.todolist.presentation.list.ListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { UpdateToDoItemUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { InsertToDoItemUseCase(get()) }

    viewModel { ListViewModel(get(), get(), get()) }
}