package com.josemarrima.contactsplus.data

import org.junit.Assert.*
import org.junit.Before

class DefaultContactRepositoryTest {

    private val contact1 = Contact("Nino",
        "Panda",
        "82000000",
        "email@gmail.com", "Tech1", "Maputo", "photoURL")

    private val contact2 = Contact("Baby",
        "Pandaless",
        "82000001",
        "email@gmail.com", "Tech1", "Vienna", "photoURL")

    private val contact3 = Contact("Ash",
        "PandaBoss",
        "82000002",
        "emailash@gmail.com", "Tech1", "Hyderabad", "photoURL")

    private val localContacts = listOf(contact1, contact2, contact3).sortedBy { it.name }

    private lateinit var contactsLocalDataSource: FakeDataSource

    // Class under test
    private lateinit var contactRepository: ContactRepository

    @Before
    fun createRepository() {
        contactsLocalDataSource = FakeDataSource(localContacts.toMutableList())

        contactRepository = DefaultContactRepository(contactsLocalDataSource)
    }
}