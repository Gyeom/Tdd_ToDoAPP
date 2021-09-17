package com.study.todolist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.study.todolist.data.db.dao.ToDoDao
import com.study.todolist.data.entity.ToDoEntity

@Database(
    entities = [ToDoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ToDoDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "ToDoDataBase.db"
    }

    abstract fun toDoDao(): ToDoDao

}
