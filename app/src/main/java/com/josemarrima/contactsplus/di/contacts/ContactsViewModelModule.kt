package com.josemarrima.contactsplus.di.contacts

import androidx.lifecycle.ViewModel
import com.josemarrima.contactsplus.di.ViewModelKey
import com.josemarrima.contactsplus.ui.contacts.ContactsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContactsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContactsViewModel::class)
    abstract fun bindContactsViewModel(contactsViewModel: ContactsViewModel): ViewModel
}