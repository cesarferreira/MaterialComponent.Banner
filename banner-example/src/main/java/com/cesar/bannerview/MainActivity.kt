package com.cesar.bannerview

import android.os.Bundle
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
//        banner.

    }

    private fun setupRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        adapter = ListItemsAdapter(getFakeItems() as ArrayList<ItemViewModel>)

        recyclerView.adapter = adapter
    }

    private fun getFakeItems() = (0..500).map { ItemViewModel(it, Faker.getRandomImage(300, 400)) }.toList()

}
