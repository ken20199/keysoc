package com.test.assessment.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.infotronic.tech.facade.base.BaseFragment
import com.test.assessment.R
import com.test.assessment.model.AlbumResponse
import com.test.assessment.model.Result
import com.test.assessment.service.HomePageService
import com.test.assessment.view.adapter.AlbumAdapter
import com.test.assessment.viewModel.MainContentViewModel
import kotlinx.android.synthetic.main.album_list.*
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment() {

    lateinit var mainContentViewModel: MainContentViewModel
    override val layout: Int = R.layout.home_fragment
    var results = arrayListOf<Result>()
    private lateinit var albumAdapter: AlbumAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        results = HomePageService.genLoadingList() as ArrayList<Result>

        initUIControl()

        mainContentViewModel.callAlbumApi("jack johnson", "album")

    }

    override fun initUIControl() {
        setAdapter()
    }


    private fun setAdapter() {
        albumAdapter = AlbumAdapter(requireContext(), results)
        rvAlbum.layoutManager = LinearLayoutManager(requireContext())
        rvAlbum.adapter = albumAdapter
    }

    override fun subscribeViewModel() {
        mainContentViewModel = ViewModelProviders.of(this)[MainContentViewModel::class.java]

        mainContentViewModel.albumLiveData.observe(this, Observer { albumResponse ->
            results.clear()
            albumResponse.results.forEach {
                results.add(it)
            }
            albumAdapter.notifyDataSetChanged()
        })
    }



}