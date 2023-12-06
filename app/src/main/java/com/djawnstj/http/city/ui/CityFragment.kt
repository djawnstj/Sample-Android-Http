package com.djawnstj.http.city.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.djawnstj.http.city.adapter.CityListAdapter
import com.djawnstj.http.city.dto.City
import com.djawnstj.http.city.dto.request.CityCreateRequest
import com.djawnstj.http.city.dto.response.CityCreateResponse
import com.djawnstj.http.city.dto.response.CityDeleteResponse
import com.djawnstj.http.city.repository.cityApi
import com.djawnstj.http.common.showToast
import com.djawnstj.http.common.ui.BaseFragment
import com.djawnstj.http.databinding.FragmentCityBinding
import com.djawnstj.http.menu.ui.MenuFragment
import kotlinx.coroutines.*

class CityFragment : BaseFragment<FragmentCityBinding>(FragmentCityBinding::inflate) {

    companion object {
        private const val TAG = "CityFragment"
    }

    private val cityListAdapter: CityListAdapter by lazy {
        CityListAdapter().also { adapter ->
            adapter.setOnCityHolderClickListener { id ->
                id?.let { deleteCity(it) }
            }
        }
    }

    override fun initFragment() {
        initCityListRecyclerView()
        initClickListener()
        setCityList()
    }

    private fun initClickListener() {
        binding.cityCreateButton.setOnClickListener {
            createNewCity()
        }

        binding.refreshButton.setOnClickListener {
            setCityList()
        }
    }

    private fun initCityListRecyclerView() {
        binding.cityListRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.cityListRecyclerView.adapter = cityListAdapter
    }

    private fun deleteCity(id: Long) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = callCityDelete(id)


            if (response == null) {
                showToast(requireContext(), "도시 삭제에 실패했습니다.")

                return@launch
            }

            setCityList()
        }
    }

    private suspend fun callCityDelete(id: Long): CityDeleteResponse? {
        val response = coroutineScope { async { cityApi.deleteCity(id) } }.await()

        return response.body()?.data?.body
    }

    private fun createNewCity() {
        val name = binding.nameInput.text.toString()
        val code = binding.codeInput.text.toString().toInt()

        val newCity = City(name = name, code = code)

        CoroutineScope(Dispatchers.Main).launch {
            val response = callCityCreate(newCity)

            if (response == null) {
                showToast(requireContext(), "도시 생성에 실패했습니다.")

                return@launch
            }

            showToast(requireContext(), response.message)

            listener?.hideSoftKeyboard()

            setCityList()
            clearNewCityInput()
        }
    }

    private fun clearNewCityInput() {
        binding.nameInput.setText("")
        binding.codeInput.setText("")
    }

    private suspend fun callCityCreate(newCity: City): CityCreateResponse? {
        val response = coroutineScope { async { cityApi.createCity(CityCreateRequest(newCity.name, newCity.code)) } }.await()

        return response.body()?.data?.body
    }

    private fun setCityList() {
        CoroutineScope(Dispatchers.Main).launch {
            val callCityList = callCityList()

            cityListAdapter.submitList(callCityList)
        }
    }

    private suspend fun callCityList(): List<City> {
        val response = coroutineScope { async { cityApi.getCityList() } }.await()

        return response.body()?.data?.body.orEmpty()
    }

    override fun onBackPressed() {
        listener?.changeFragment(MenuFragment())
    }

}