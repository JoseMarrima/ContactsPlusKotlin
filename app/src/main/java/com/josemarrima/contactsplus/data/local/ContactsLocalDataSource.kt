package com.josemarrima.contactsplus.data.local

import androidx.lifecycle.LiveData
import com.josemarrima.contactsplus.data.Contact
import com.josemarrima.contactsplus.data.ContactsDataSource
import timber.log.Timber
import javax.inject.Inject

class ContactsLocalDataSource @Inject constructor(private val contactDao: ContactDao) :
    ContactsDataSource {

    override fun getAllContacts() : LiveData<List<Contact>?> {
        Timber.d(contactDao.getAllContacts().value.toString())
        return contactDao.getAllContacts()
    }

    override fun getContactById(contactId: Int) : LiveData<Contact?> = contactDao.getContactById(contactId)

    override suspend fun insertContact(contact: Contact) = contactDao.insertContact(contact)

    override suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)

    override suspend fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)

    override fun findByName(query: String): LiveData<List<Contact>> = contactDao.findByName(query)

}