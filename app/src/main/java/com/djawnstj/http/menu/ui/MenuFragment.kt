package com.djawnstj.http.menu.ui

import com.djawnstj.http.city.ui.CityFragment
import com.djawnstj.http.common.ui.BaseFragment
import com.djawnstj.http.databinding.FragmentMenuBinding
import com.djawnstj.http.person.ui.PersonFragment

class MenuFragment: BaseFragment<FragmentMenuBinding>(FragmentMenuBinding::inflate) {

    override fun initFragment() {

        initViewClickListeners()
    }

    private fun initViewClickListeners() {

        binding.personMenuButton.setOnClickListener {
            listener?.changeFragment(PersonFragment())
        }

        binding.cityMenuButton.setOnClickListener {
            listener?.changeFragment(CityFragment())
        }

    }

    override fun onBackPressed() {
    }

}