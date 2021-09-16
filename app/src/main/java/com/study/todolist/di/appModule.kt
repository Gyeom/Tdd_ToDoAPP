package com.study.todolist.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

internal val appModule = module {
    single { Dispatchers.Main }
    single { Dispatchers.IO }
}