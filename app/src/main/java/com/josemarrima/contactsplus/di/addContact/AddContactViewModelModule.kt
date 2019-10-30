package com.josemarrima.contactsplus.di.addContact

import androidx.lifecycle.ViewModel
import com.josemarrima.contactsplus.di.ViewModelKey
import com.josemarrima.contactsplus.ui.addContact.AddContactViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AddContactViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddContactViewModel::class)
    abstract fun bindAddContactViewModel(addContactViewModel: AddContactViewModel): ViewModel
}