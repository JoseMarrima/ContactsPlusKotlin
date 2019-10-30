package com.josemarrima.contactsplus.ui.contactDetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.josemarrima.contactsplus.R
import com.josemarrima.contactsplus.databinding.ContactDetailsFragmentBinding
import com.josemarrima.contactsplus.di.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ContactDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: ContactDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding : ContactDetailsFragmentBinding = DataBindingUtil.
            inflate(inflater, R.layout.contact_details_fragment, container, false)
        binding.setLifecycleOwner(this.viewLifecycleOwner)

        viewModel = ViewModelProviders.of(this, factory).get(ContactDetailsViewModel::class.java)

        val args = ContactDetailsFragmentArgs.fromBundle(arguments!!)
        val contact = viewModel.getContactById(args.id)
        contact.observe(this, Observer {
            binding.content.contact = it
        })

        binding.detailsFab.setOnClickListener {
            it.findNavController().navigate(ContactDetailsFragmentDirections
                .actionContactDetailsFragmentToAddContactFragment(args.id))
        }

        return binding.root
    }

}
