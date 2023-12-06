package com.djawnstj.http.person.ui

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.djawnstj.http.common.api.dto.response.BaseResponse
import com.djawnstj.http.common.showToast
import com.djawnstj.http.common.ui.BaseFragment
import com.djawnstj.http.common.utils.error
import com.djawnstj.http.databinding.FragmentPersonBinding
import com.djawnstj.http.menu.ui.MenuFragment
import com.djawnstj.http.person.adapter.PersonListAdapter
import com.djawnstj.http.person.dto.Person
import com.djawnstj.http.person.dto.request.PersonCreateRequest
import com.djawnstj.http.person.dto.response.PersonCreateResponse
import com.djawnstj.http.person.repository.personApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PersonFragment: BaseFragment<FragmentPersonBinding>(FragmentPersonBinding::inflate) {

    companion object {
        private const val TAG = "PersonFragment"
    }

    private val personListAdapter: PersonListAdapter by lazy { PersonListAdapter() }

    override fun initFragment() {
        initPersonListRecyclerView()
        initClickListener()
        callPersonList()
    }

    private fun initClickListener() {
        binding.personCreateButton.setOnClickListener {
            createNewPerson()
        }

        binding.refreshButton.setOnClickListener {
            callPersonList()
        }
    }

    private fun initPersonListRecyclerView() {
        binding.personListRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.personListRecyclerView.adapter = personListAdapter
    }

    private fun createNewPerson() {
        val name = binding.nameInput.text.toString()
        val age = binding.ageInput.text.toString().toInt()
        val address = binding.addressInput.text.toString()

        val newPerson = Person(name = name, age = age, address = address)

        callPersonCreate(newPerson)
    }

    private fun clearNewPersonInput() {
        binding.nameInput.setText("")
        binding.ageInput.setText("")
        binding.addressInput.setText("")
    }

    private fun callPersonCreate(newPerson: Person) {
        personApi.createPerson(PersonCreateRequest(newPerson.name, newPerson.age, newPerson.address))
            .enqueue(object: Callback<BaseResponse<PersonCreateResponse>> {
                override fun onResponse(call: Call<BaseResponse<PersonCreateResponse>>, response: Response<BaseResponse<PersonCreateResponse>>) {
                    val message = response.body()?.data?.body?.message
                    showToast(requireContext(), message)

                    listener?.hideSoftKeyboard()

                    callPersonList()
                    clearNewPersonInput()
                }

                override fun onFailure(call: Call<BaseResponse<PersonCreateResponse>>, t: Throwable) {
                    error(TAG, "callPersonCreate onFailure", t)
                }

            })

    }

    private fun callPersonList() {
        personApi.getPersonList()
            .enqueue(object: Callback<BaseResponse<List<Person>>> {
                override fun onResponse(call: Call<BaseResponse<List<Person>>>, response: Response<BaseResponse<List<Person>>>) {
                    val persons = response.body()?.data?.body.orEmpty()
                    personListAdapter.submitList(persons)
                }

                override fun onFailure(call: Call<BaseResponse<List<Person>>>, t: Throwable) {
                    error(TAG, "callPersonList onFailure", t)
                }

            })
    }

    override fun onBackPressed() {
        listener?.changeFragment(MenuFragment())
    }
}