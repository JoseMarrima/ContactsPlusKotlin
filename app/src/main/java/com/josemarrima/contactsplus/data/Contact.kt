package com.josemarrima.contactsplus.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(var name: String?,
                   var surname: String?,
                   var phoneNumber: String?,
                   var email: String?,
                   var company: String?,
                   var location: String?,
                   var imageUrl: String?,
                   @PrimaryKey(autoGenerate = true)
                   val id: Int = 0)