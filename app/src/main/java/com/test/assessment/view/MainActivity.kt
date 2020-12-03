package com.test.assessment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import com.infotronic.tech.facade.base.BaseActivity
import com.infotronic.tech.facade.base.BaseFragment
import com.test.assessment.R
import com.test.assessment.viewModel.MainContentViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var mainContentViewModel: MainContentViewModel
    var fm = supportFragmentManager
    var homefragment = HomeFragment()
    var bookmarkFragment = BookmarkFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUIControl()

        subscribeViewModel()

    }


    override fun initUIControl() {

        setSupportActionBar(findViewById(R.id.tbbar))
        supportActionBar?.title = "Albums"

        setupBottomNavBar()


    }

    private fun setupBottomNavBar() {
        bnvMain.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.btnHome -> {
                    showFragment(homefragment)
                    hideFragment(bookmarkFragment)
                    true
                }
                R.id.btnBookmark -> {
                    showFragment(bookmarkFragment)
                    hideFragment(homefragment)
                    true
                }
            }

            false
        }


        active = homefragment

        fm.let {
            it.apply {
                addFragment(homefragment)
                addFragment(bookmarkFragment)
                hideFragment(bookmarkFragment)
            }
        }

    }

    private fun subscribeViewModel() {
        mainContentViewModel = ViewModelProviders.of(this)[MainContentViewModel::class.java]

        mainContentViewModel.albumLiveData.observe(this, Observer {
            printLog("***" + it)
        })
    }


}