package com.josemarrima.contactsplus.di

import android.content.Context
import androidx.room.Room
import com.josemarrima.contactsplus.data.ContactRepository
import com.josemarrima.contactsplus.data.local.ContactDao
import com.josemarrima.contactsplus.data.local.ContactDatabase
import com.josemarrima.contactsplus.utilities.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideContactDao(contactDatabase: ContactDatabase): ContactDao {
        return contactDatabase.contactDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideContactDatabase(context: Context): ContactDatabase {
        return Room.databaseBuilder(context,
            ContactDatabase::class.java, DATABASE_NAME).build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideContactRepository(contactDao: ContactDao): ContactRepository {
        return ContactRepository(contactDao)
    }
}