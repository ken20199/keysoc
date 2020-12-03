package com.test.assessment.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.infotronic.tech.facade.base.BaseFragment
import com.orhanobut.hawk.Hawk
import com.test.assessment.R
import com.test.assessment.constract.BOOKMARK_LIST
import com.test.assessment.model.Result
import com.test.assessment.service.HomePageService
import com.test.assessment.view.adapter.AlbumAdapter
import kotlinx.android.synthetic.main.home_fragment.*

class BookmarkFragment : BaseFragment() {
    override val layout: Int = R.layout.bookmark_fragment
    private lateinit var albumAdapter: AlbumAdapter
    var results = arrayListOf<Result>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        results = Hawk.get(BOOKMARK_LIST, arrayListOf())
//
//        initUIControl()

    }

    override fun subscribeViewModel() {

    }



    override fun initUIControl() {
        setAdapter()
    }

    private fun setAdapter() {
        albumAdapter = AlbumAdapter(requireContext(), results,true)
        rvAlbum.layoutManager = LinearLayoutManager(requireContext())
        rvAlbum.adapter = albumAdapter
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden){
            printLog("haha is show")
            results = Hawk.get(BOOKMARK_LIST, arrayListOf())
            setAdapter()
        }else{
            printLog("haha is hide")
        }
    }

}