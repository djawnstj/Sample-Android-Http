package com.djawnstj.http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.djawnstj.http.common.ui.BaseFragment
import com.djawnstj.http.common.ui.FragmentListener
import com.djawnstj.http.databinding.ActivityMainBinding
import com.djawnstj.http.menu.ui.MenuFragment

class MainActivity : AppCompatActivity(), FragmentListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initFragmentContainer()
    }

    private fun initFragmentContainer() {
        changeFragment(MenuFragment())
    }

    override fun changeFragment(fragment: BaseFragment<*>) {
        supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id, fragment).commit()
    }

    override fun hideSoftKeyboard() {
        val manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
        manager!!.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}