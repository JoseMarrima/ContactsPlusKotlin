package com.josemarrima.contactsplus.di.contactDetails

import androidx.lifecycle.ViewModel
import com.josemarrima.contactsplus.di.ViewModelKey
import com.josemarrima.contactsplus.ui.contactDetails.ContactDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContactDetailsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContactDetailsViewModel::class)
    abstract fun bindContactDetailsViewModel(contactDetailsViewModel: ContactDetailsViewModel): ViewModel
}