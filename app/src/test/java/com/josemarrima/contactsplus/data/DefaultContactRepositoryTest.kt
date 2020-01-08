package com.josemarrima.contactsplus.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.josemarrima.contactsplus.LiveDataTestUtil
import org.junit.Rule

@ExperimentalCoroutinesApi
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


    private val newContact = Contact("new",
        "contact",
        "87000002",
        "new@gmail.com", "new company", "newyork", "new")

    private val localContacts = listOf(contact1, contact2, contact3).sortedBy { it.name }

    private lateinit var contactsLocalDataSource: FakeDataSource

    // Class under test
    private lateinit var contactRepository: ContactRepository


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        contactsLocalDataSource = FakeDataSource(localContacts.toMutableList())

        contactRepository = DefaultContactRepository(contactsLocalDataSource)
    }

    @Test
    fun insertContact_savesToLocalDatabase() = runBlockingTest {
        assertThat(contactsLocalDataSource.contacts).doesNotContain(newContact)
        val value = LiveDataTestUtil.getValue(contactRepository.getAllContacts())
        assertThat(value).doesNotContain(newContact)

        contactRepository.insertContact(newContact)

        val result = LiveDataTestUtil.getValue(contactRepository.getAllContacts())
        assertThat(result).contains(newContact)
    }

    @Test
    fun getContacts_FromLocalDataSource() = runBlockingTest {
        val contacts = LiveDataTestUtil.getValue(contactRepository.getAllContacts())

        assertThat(contacts).isEqualTo(localContacts)
    }

    @Test
    fun deleteContact_FromListOfContacts() = runBlockingTest {
        contactRepository.deleteContact(contact1)

        val contacts = LiveDataTestUtil.getValue(contactRepository.getAllContacts())

        assertThat(contacts).doesNotContain(contact1)
    }
}