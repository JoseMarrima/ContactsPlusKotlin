package com.josemarrima.contactsplus.ui.addContact

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.josemarrima.contactsplus.data.Contact
import com.josemarrima.contactsplus.data.ContactRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddContactViewModel @Inject constructor(private val repository: ContactRepository) : ViewModel() {

    var name = ""

    var surname = ""

    var phone = ""

    var email = ""

    var company = ""

    var location = ""

    fun saveContact(contact: Contact) {
        insertContact(contact)
    }

    private fun insertContact(contact: Contact) {
        viewModelScope.launch {
            repository.insertContact(contact)
        }
    }

    fun updateContact(contact: Contact) {
        updateRepoContact(contact)
    }

    private fun updateRepoContact(contact: Contact) {
        viewModelScope.launch {
            repository.updateContact(contact)
        }
    }

    fun getContactById(id: Int) = repository.getContactById(id)
}
