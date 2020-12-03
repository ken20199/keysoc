package com.test.assessment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.infotronic.tech.facade.base.BaseActivity
import com.test.assessment.R
import com.test.assessment.viewModel.MainContentViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var mainContentViewModel: MainContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeViewModel()

//        btn_test.setOnClickListener {
//            mainContentViewModel.callAlbumApi("jack johnson","album")
//        }
    }

    private fun subscribeViewModel() {
        mainContentViewModel = ViewModelProviders.of(this)[MainContentViewModel::class.java]

        mainContentViewModel.albumLiveData.observe(this, Observer {
            printLog("***" + it)
        })
    }


}