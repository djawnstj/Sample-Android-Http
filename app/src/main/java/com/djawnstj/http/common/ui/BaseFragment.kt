package com.djawnstj.http.common.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T: ViewBinding>(private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> T) : Fragment() {

    private var _binding: T? = null
    val binding get() = _binding!!

    protected var listener: FragmentListener? = null
    
    private var callback: OnBackPressedCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Activity 리스너 초기화
        if (context is FragmentListener) { listener = context }

        // Activity onBackPressedCallback 초기화
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
        callback?.let { activity?.onBackPressedDispatcher?.addCallback(this, it) }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    abstract fun initFragment()

    abstract fun onBackPressed()

    override fun onDetach() {
        super.onDetach()
        callback?.remove()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}