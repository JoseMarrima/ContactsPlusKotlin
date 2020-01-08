package com.josemarrima.contactsplus.data

import androidx.lifecycle.LiveData
import com.josemarrima.contactsplus.data.local.ContactDao
import com.josemarrima.contactsplus.data.local.ContactsLocalDataSource
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultContactRepository @Inject constructor(private val localDataSource: ContactsDataSource) :
    ContactRepository {

    override fun getAllContacts() : LiveData<List<Contact>?> {
        Timber.d(localDataSource.getAllContacts().value.toString())
        return localDataSource.getAllContacts()
    }

    override fun getContactById(contactId: Int) : LiveData<Contact?> = localDataSource.getContactById(contactId)

    override suspend fun insertContact(contact: Contact) = localDataSource.insertContact(contact)

    override suspend fun updateContact(contact: Contact) = localDataSource.updateContact(contact)

    override suspend fun deleteContact(contact: Contact) = localDataSource.deleteContact(contact)

    override fun findByName(query: String): LiveData<List<Contact>> = localDataSource.findByName(query)

}