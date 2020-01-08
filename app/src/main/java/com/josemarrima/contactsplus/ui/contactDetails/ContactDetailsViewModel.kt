package com.josemarrima.contactsplus.ui.contactDetails

import androidx.lifecycle.ViewModel;
import com.josemarrima.contactsplus.data.DefaultContactRepository
import javax.inject.Inject

class ContactDetailsViewModel @Inject constructor(private val repositoryDefault: DefaultContactRepository): ViewModel() {
    fun getContactById(id: Int) = repositoryDefault.getContactById(id)
}
