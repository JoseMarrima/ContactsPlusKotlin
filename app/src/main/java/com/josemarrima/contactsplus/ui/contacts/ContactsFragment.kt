package com.josemarrima.contactsplus.ui.contacts

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.josemarrima.contactsplus.R
import com.josemarrima.contactsplus.databinding.ContactsFragmentBinding
import com.josemarrima.contactsplus.di.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ContactsFragment : DaggerFragment(), SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {


    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: ContactsViewModel
    private lateinit var searchView: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = ContactsFragmentBinding
            .inflate(inflater, container, false)

        viewModel = ViewModelProviders.of(this, factory).get(ContactsViewModel::class.java)

        val adapter = ContactsAdapter(ClickListener {
            val id = it.id
            this.findNavController().navigate(ContactsFragmentDirections
                .actionContactsFragmentToContactDetailsFragment(id))
        })
        binding.contactList.adapter = adapter

        subscribeUI(adapter)

        binding.contactsFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_contactsFragment_to_addContactFragment)
        }

        binding.contactsBar.replaceMenu(R.menu.menu)

        setHasOptionsMenu(true)
        return binding.root
    }


    private fun subscribeUI(adapter: ContactsAdapter) {
        viewModel.allContacts.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)

        searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.findByName(query!!)
        return true
    }

    override fun onQueryTextChange(newText: String?) = true

    override fun onClose(): Boolean {
        viewModel.allContacts
        searchView.onActionViewCollapsed()
        return true
    }

}
