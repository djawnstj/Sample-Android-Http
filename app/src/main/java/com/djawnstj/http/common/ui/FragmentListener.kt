package com.djawnstj.http.common.ui

interface FragmentListener {

    /**
     * 프래그먼트 전환 함수(replace)
     */
    fun changeFragment(fragment: BaseFragment<*>)

    /**
     * 소프트 키보드 숨기기
     */
    fun hideSoftKeyboard()

}