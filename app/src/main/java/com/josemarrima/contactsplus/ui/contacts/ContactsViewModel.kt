package com.josemarrima.contactsplus.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.josemarrima.contactsplus.data.Contact
import com.josemarrima.contactsplus.data.ContactRepository
import javax.inject.Inject

class ContactsViewModel @Inject constructor(private val repository: ContactRepository): ViewModel() {

    val allContacts = repository.getAllContacts()

    fun findByName(query: String): LiveData<List<Contact>> {
        return repository.findByName(query)
    }
}
