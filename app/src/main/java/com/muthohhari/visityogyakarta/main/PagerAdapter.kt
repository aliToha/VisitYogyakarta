package com.muthohhari.visityogyakarta.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.muthohhari.visityogyakarta.Account.AccountFragment
import com.muthohhari.visityogyakarta.home.HomeFragment

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when (position) {
            0 -> fragment = HomeFragment()
            1 -> fragment = AccountFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }
}