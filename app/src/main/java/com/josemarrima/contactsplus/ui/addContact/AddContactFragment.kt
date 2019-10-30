package com.josemarrima.contactsplus.ui.addContact

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.josemarrima.contactsplus.R
import com.josemarrima.contactsplus.data.Contact
import com.josemarrima.contactsplus.databinding.AddContactFragmentBinding
import com.josemarrima.contactsplus.di.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class AddContactFragment : DaggerFragment() {

    val REQUEST_IMAGE_GET = 1

    var imagePath: String? = null

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: AddContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : AddContactFragmentBinding = DataBindingUtil
            .inflate(inflater, R.layout.add_contact_fragment, container, false)
        binding.setLifecycleOwner(this.viewLifecycleOwner)

        viewModel = ViewModelProviders.of(this, factory).get(AddContactViewModel::class.java)

        val args = AddContactFragmentArgs.fromBundle(arguments!!)

        Timber.d("ID FROM DETAILS ${args.id}")

        if (args.id != -1) {
            val contact = viewModel.getContactById(args.id)
            contact.observe(this, Observer {
                binding.addContent.contact = it
            })
        }

        binding.addContent?.firstNameEdit?.doAfterTextChanged {
            viewModel.name = it.toString()
        }

        binding.addContent?.surnameEdit?.doAfterTextChanged {
            viewModel.surname = it.toString()
        }

        binding.addContent?.phoneEdit?.doAfterTextChanged {
            viewModel.phone = it.toString()
        }

        binding.addContent?.emailEdit?.doAfterTextChanged {
            viewModel.email = it.toString()
        }

        binding.addContent?.companyEdit?.doAfterTextChanged {
            viewModel.company = it.toString()
        }

        binding.addContent?.addressEdit?.doAfterTextChanged {
            viewModel.location = it.toString()
        }

        binding.addContent.uploadButton.setOnClickListener {
            selectImage()
        }



        binding.addFab.setOnClickListener {
            if (args.id == -1) {
                Timber.d("Hello this contact name is ${viewModel.name} ${viewModel.surname}")
                viewModel.saveContact( Contact(viewModel.name,
                    viewModel.surname,
                    viewModel.phone,
                    viewModel.email,
                    viewModel.company,
                    viewModel.location,
                    imagePath))
                it.findNavController().navigate(AddContactFragmentDirections
                    .actionAddContactFragmentToContactsFragment())
            } else {
                Timber.d("Hello this contact name is ${viewModel.name} ${viewModel.surname}")
                viewModel.updateContact( Contact(viewModel.name,
                    viewModel.surname,
                    viewModel.phone,
                    viewModel.email,
                    viewModel.company,
                    viewModel.location,
                    imagePath,
                    args.id))
                it.findNavController().navigate(AddContactFragmentDirections
                    .actionAddContactFragmentToContactsFragment())

            }
        }

        return binding.root
    }

    fun selectImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        if (intent.resolveActivity(activity!!.packageManager) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_GET && resultCode == Activity.RESULT_OK) {
            val fullPhotoUri: Uri? = data?.data
            Timber.d("URI ${fullPhotoUri}")
            // Do work with photo saved at fullPhotoUri
            imagePath = fullPhotoUri.toString()
            Timber.d("URI string ${imagePath?.toUri() is Uri}")
        }
    }

}
