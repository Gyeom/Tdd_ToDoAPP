package com.study.todolist.di

import com.study.todolist.domain.todo.GetToDoListUseCase
import com.study.todolist.domain.todo.InsertToDoListUseCase
import com.study.todolist.presentation.list.ListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }

    viewModel { ListViewModel(get()) }
}