package com.study.todolist.di

import com.study.todolist.data.repository.ToDoRepository
import com.study.todolist.domain.todo.*
import com.study.todolist.livedata.repository.TestToDoRepository
import com.study.todolist.presentation.list.ListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { UpdateToDoItemUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }

    single<ToDoRepository> { TestToDoRepository() }

    viewModel { ListViewModel(get(), get(), get()) }
}
