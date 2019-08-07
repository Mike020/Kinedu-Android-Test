package com.miguel.kineduandroidtest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.miguel.kineduandroidtest.R
import com.miguel.kineduandroidtest.view.adapters.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , AdapterView.OnItemSelectedListener {

    private val ageRange = arrayOf("ALL MONTHS", "0-1 MONTH", "2 MONTHS" ,"3 MONTHS","4 MONTHS",
        "5 MONTHS","6 MONTHS","7 MONTHS","8 MONTHS","9 MONTHS","10 MONTHS","11 MONTHS","12 MONTHS",
        "13 MONTHS","14 MONTHS","15 MONTHS","16 MONTHS","17 MONTHS","18 MONTHS","19 MONTHS","20 MONTHS",
        "21 MONTHS","22 MONTHS","23 MONTHS","24 MONTHS")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        ageSpinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val arrayAdapter = ArrayAdapter(this, R.layout.spinner_style, ageRange)
        // Set layout to use when the list of choices appear
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        // Set Adapter to Spinner
        ageSpinner!!.setAdapter(arrayAdapter)
    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {

        // use position to know the selected item
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }
}




