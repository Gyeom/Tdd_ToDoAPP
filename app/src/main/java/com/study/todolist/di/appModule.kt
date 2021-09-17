package com.study.todolist.di

import android.content.Context
import androidx.room.Room
import com.study.todolist.data.db.ToDoDatabase
import com.study.todolist.data.repository.DefaultToDoRepository
import com.study.todolist.data.repository.ToDoRepository
import com.study.todolist.domain.todo.*
import com.study.todolist.domain.todo.DeleteAllToDoItemUseCase
import com.study.todolist.domain.todo.GetToDoListUseCase
import com.study.todolist.domain.todo.InsertToDoListUseCase
import com.study.todolist.domain.todo.InsertToDoItemUseCase
import com.study.todolist.domain.todo.UpdateToDoItemUseCase
import com.study.todolist.presentation.detail.DetailMode
import com.study.todolist.presentation.detail.DetailViewModel
import com.study.todolist.presentation.list.ListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }

    factory { GetToDoListUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { InsertToDoItemUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { UpdateToDoItemUseCase(get()) }

    single<ToDoRepository> { DefaultToDoRepository(get(), get()) }

    single { provideDB(androidApplication()) }
    single { provideToDoDao(get()) }

    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) -> DetailViewModel(detailMode, id, get(), get(), get(), get()) }

}

internal fun provideDB(context: Context): ToDoDatabase =
    Room.databaseBuilder(context, ToDoDatabase::class.java, ToDoDatabase.DB_NAME).build()

internal fun provideToDoDao(database: ToDoDatabase) = database.toDoDao()
