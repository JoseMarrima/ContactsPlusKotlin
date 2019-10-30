package com.josemarrima.contactsplus.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.josemarrima.contactsplus.data.Contact

@Dao
interface ContactDao {
    @Query("SELECT * from contact ORDER BY name DESC")
    fun getAllContacts() : LiveData<List<Contact>?>

    @Query("SELECT * from contact WHERE id = :contactId")
    fun getContactById(contactId: Int) : LiveData<Contact?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * from contact WHERE name or surname LIKE '%' || :query || '%'")
    fun findByName(query: String) : LiveData<List<Contact>>
}