package com.josemarrima.contactsplus.data

import androidx.lifecycle.LiveData
import com.josemarrima.contactsplus.data.local.ContactDao
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor(private val contactDao: ContactDao) {

    fun getAllContacts() : LiveData<List<Contact>?> {
        Timber.d(contactDao.getAllContacts().value.toString())
        return contactDao.getAllContacts()
    }

    fun getContactById(contactId: Int) : LiveData<Contact?> = contactDao.getContactById(contactId)

    suspend fun insertContact(contact: Contact) = contactDao.insertContact(contact)

    suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)

    suspend fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)

    fun findByName(query: String): LiveData<List<Contact>> = contactDao.findByName(query)

}