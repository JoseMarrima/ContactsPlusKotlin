package com.josemarrima.contactsplus.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeDataSource(var  contacts: MutableList<Contact>? = mutableListOf()) : ContactsDataSource {

    private val observableContacts = MutableLiveData<List<Contact>>()

    override fun getAllContacts(): LiveData<List<Contact>?> {
        observableContacts.value = contacts
        return observableContacts
    }

    override fun getContactById(contactId: Int): LiveData<Contact?> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insertContact(contact: Contact) {
        contacts?.add(contact)
    }

    override suspend fun updateContact(contact: Contact) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteContact(contact: Contact) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findByName(query: String): LiveData<List<Contact>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}