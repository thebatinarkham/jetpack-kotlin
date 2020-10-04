package com.example.kotlin.data

import android.content.Context
import androidx.room.*
import com.example.kotlin.model.ToDoData

@Database(entities = [ToDoData::class],version = 1,exportSchema = false)
@TypeConverters(Converter::class)
abstract class TodoDatabase :RoomDatabase(){


    abstract fun todoDao():TodoDao

    companion object{
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context):TodoDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).build()

                INSTANCE = instance
                return  instance
            }
        }
    }
}