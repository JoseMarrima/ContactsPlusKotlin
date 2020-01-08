package com.josemarrima.contactsplus.data

import androidx.lifecycle.LiveData

interface ContactsDataSource {

    fun getAllContacts() : LiveData<List<Contact>?>

    fun getContactById(contactId: Int) : LiveData<Contact?>

    suspend fun insertContact(contact: Contact)

    suspend fun updateContact(contact: Contact)

    suspend fun deleteContact(contact: Contact)

    fun findByName(query: String): LiveData<List<Contact>>
}