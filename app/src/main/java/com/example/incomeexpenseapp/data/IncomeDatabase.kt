package com.example.incomeexpenseapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.incomeexpenseapp.model.Income

@Database(entities = [Income::class], version = 1, exportSchema = false)
abstract class IncomeDatabase: RoomDatabase() {

    abstract fun incomeDao(): IncomeDao

    companion object{
        @Volatile
        private var INSTANCE: IncomeDatabase? = null

        fun getDatabase(context: Context): IncomeDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IncomeDatabase::class.java,
                    "income_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}