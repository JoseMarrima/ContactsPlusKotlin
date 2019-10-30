package com.josemarrima.contactsplus.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.josemarrima.contactsplus.data.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactDao() : ContactDao

}