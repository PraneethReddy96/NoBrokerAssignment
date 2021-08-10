package com.example.nobroker.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nobroker.R
import com.example.nobroker.adapter.SearchActivityAdapter
import com.example.nobroker.data.database.NoBrokerDataEntity
import com.example.nobroker.utils.onItemClickListener
import com.example.nobroker.viewmodel.NoBrokerViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), onItemClickListener {

    private val viewModel by viewModels<NoBrokerViewModel>()
    var detailsList: MutableList<NoBrokerDataEntity> = mutableListOf()
    var searchDataList: MutableList<NoBrokerDataEntity> = mutableListOf()
    lateinit var searchAdapter: SearchActivityAdapter
    val emptyDataBase = 1;
    var rvSearchActivity: RecyclerView? = null
    lateinit var shimmerFrameLayout  : ShimmerFrameLayout
    lateinit var sbSearchBar : SearchView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NoBroker)
        setContentView(R.layout.activity_search)
        initializeViewModelAndRepository()
        checkDataBase()
        bindRecyclerViewDataAndAdapter()
        buildRecyclerViewData()
        createSearchView()
    }

    /*
   Function which uses viewModel to observe the data and build it into the list for recycler view.
   */
    private fun buildRecyclerViewData() {


        viewModel.retrieveNoBrokerDataEntity().observe(this, Observer {


            detailsList.clear()
            detailsList.addAll(it)
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
            searchDataList.addAll(detailsList)
            rvSearchActivity?.visibility = View.VISIBLE
            searchAdapter.notifyDataSetChanged()


        })

    }


    /*
Function which checks the database, before inserting the values into the table.
     */
    private fun checkDataBase() {

        viewModel.checkDataBase().observe(this, Observer {

            if (it == emptyDataBase) {

                addTasksToDataBase()

            }

        })


    }


    /*
Initializing the viewModel and passing he repository object into the view model.
     */

    private fun initializeViewModelAndRepository() {
       rvSearchActivity = findViewById<RecyclerView>(R.id.rvSearchActivity)
        shimmerFrameLayout = findViewById<ShimmerFrameLayout>(R.id.shimmerFrameLayout)
        sbSearchBar = findViewById<SearchView>(R.id.sbSearchBar)
    }


    /*
Function to set the adapter of the recycler view.
     */
    private fun bindRecyclerViewDataAndAdapter() {

        val llManager = LinearLayoutManager(this)
        searchAdapter = SearchActivityAdapter(searchDataList, this)
        rvSearchActivity?.layoutManager = llManager
        rvSearchActivity?.adapter = searchAdapter
    }

    /*
Used to implement the search feature from the recycler view, and fetch results.
     */
    private fun createSearchView() {

        sbSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                searchDataList.clear()
                val searchText = newText!!

                if (searchText.isNotEmpty()) {


                    detailsList.forEach {

                        if (it.title!!.contains(searchText) || it.subTitle!!.contains(searchText)) {

                            searchDataList.add(it)
                        }
                    }
                    rvSearchActivity?.adapter!!.notifyDataSetChanged()
                } else {

                    searchDataList.clear()
                    searchDataList.addAll(detailsList)
                    rvSearchActivity?.adapter!!.notifyDataSetChanged()

                }
                return false
            }
        })

    }

    /*
Function to add data into Database
     */
    private fun addTasksToDataBase() {


        viewModel.addToDataBase()

    }

    /*
function to pass data to preview activity
     */
    override fun onItemClicked(entity: NoBrokerDataEntity) {

        val intent = Intent(this, PreviewActivity::class.java)
        intent.putExtra("image", entity.image.toString())
        intent.putExtra("title", entity.title.toString())
        intent.putExtra("subTitle", entity.subTitle.toString())
        startActivity(intent)


    }


    /*
To start/stop the shimmer effect.
     */
    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmer()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmer()
        super.onPause()
    }

}