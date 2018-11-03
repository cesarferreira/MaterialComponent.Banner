package com.cesar.bannerview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import cesarferreira.faker.Faker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ListItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        setupBanner()
        fab.setOnClickListener {
            banner.show()
        }

    }

    private fun setupBanner() {
        banner.setLeftButtonAction { banner.dismiss() }
        banner.setRightButtonAction {
            Toast.makeText(applicationContext, "Perform Action please", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        adapter = ListItemsAdapter(getFakeItems() as ArrayList<ItemViewModel>)

        recyclerView.adapter = adapter
    }

    private fun getFakeItems() = (0..500).map { ItemViewModel(it, Faker.getRandomImage(300, 400)) }.toList()

}
