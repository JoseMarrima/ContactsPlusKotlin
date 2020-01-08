package com.josemarrima.contactsplus.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.josemarrima.contactsplus.data.Contact
import com.josemarrima.contactsplus.data.DefaultContactRepository
import javax.inject.Inject

class ContactsViewModel @Inject constructor(private val repositoryDefault: DefaultContactRepository): ViewModel() {

    val allContacts = repositoryDefault.getAllContacts()

    fun findByName(query: String): LiveData<List<Contact>> {
        return repositoryDefault.findByName(query)
    }
}
