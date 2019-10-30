package com.josemarrima.contactsplus.ui.contactDetails

import androidx.lifecycle.ViewModel;
import com.josemarrima.contactsplus.data.ContactRepository
import javax.inject.Inject

class ContactDetailsViewModel @Inject constructor(private val repository: ContactRepository): ViewModel() {
    fun getContactById(id: Int) = repository.getContactById(id)
}
