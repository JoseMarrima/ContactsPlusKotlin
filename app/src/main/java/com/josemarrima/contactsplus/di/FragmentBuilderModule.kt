package com.josemarrima.contactsplus.di

import com.josemarrima.contactsplus.di.addContact.AddContactViewModelModule
import com.josemarrima.contactsplus.di.contactDetails.ContactDetailsViewModelModule
import com.josemarrima.contactsplus.di.contacts.ContactsViewModelModule
import com.josemarrima.contactsplus.ui.addContact.AddContactFragment
import com.josemarrima.contactsplus.ui.contactDetails.ContactDetailsFragment
import com.josemarrima.contactsplus.ui.contacts.ContactsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [ContactsViewModelModule::class])
    abstract fun contributeContactsFragment() : ContactsFragment

    @ContributesAndroidInjector(modules = [AddContactViewModelModule::class])
    abstract fun contributeAddContactFragment() : AddContactFragment

    @ContributesAndroidInjector(modules = [ContactDetailsViewModelModule::class])
    abstract fun contributeContactDetailsFragment() : ContactDetailsFragment
}