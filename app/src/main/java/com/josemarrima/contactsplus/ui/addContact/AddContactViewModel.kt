package com.josemarrima.contactsplus.ui.addContact

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.josemarrima.contactsplus.data.Contact
import com.josemarrima.contactsplus.data.DefaultContactRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddContactViewModel @Inject constructor(private val repositoryDefault: DefaultContactRepository) : ViewModel() {

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
            repositoryDefault.insertContact(contact)
        }
    }

    fun updateContact(contact: Contact) {
        updateRepoContact(contact)
    }

    private fun updateRepoContact(contact: Contact) {
        viewModelScope.launch {
            repositoryDefault.updateContact(contact)
        }
    }

    fun getContactById(id: Int) = repositoryDefault.getContactById(id)
}
