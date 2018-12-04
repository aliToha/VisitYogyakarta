package com.muthohhari.visityogyakarta.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.muthohhari.visityogyakarta.R
import kotlinx.android.synthetic.main.activity_main.*

class Main : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    ViewPager.OnPageChangeListener {
    private lateinit var adapter: PagerAdapter
    private lateinit var menuItem: MenuItem
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adapter = PagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(this)
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.standing -> {
                viewPager.currentItem = 0
                position = 0
            }
            R.id.schedule -> {
                viewPager.currentItem = 1
                position = 1
            }
        }
        return true
    }

    override fun onPageScrollStateChanged(state: Int) {
        // do nothing
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        //do nothing
    }

    override fun onPageSelected(position: Int) {
        menuItem = bottomNavigation.menu.getItem(position).setChecked(true)
    }

}
