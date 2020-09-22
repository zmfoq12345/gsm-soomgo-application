package com.example.gsmgosu

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class VPAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    private val items: ArrayList<Fragment>
    private val page_Titles = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return items[position]
    }


    override fun getCount(): Int {
        return items.size
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return page_Titles.get(position)
    }

    init {
        items = ArrayList()
        items.add(Grade_1())
        items.add(Grade_2())
        items.add(Grade_3())
        page_Titles.add("1학년")
        page_Titles.add("2학년")
        page_Titles.add("3학년")

    }
}
