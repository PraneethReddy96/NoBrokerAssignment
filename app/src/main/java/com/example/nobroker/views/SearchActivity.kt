package com.example.nobroker.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nobroker.R
import com.example.nobroker.adapter.SearchActivityAdapter
import com.example.nobroker.data.database.NoBrokerDao
import com.example.nobroker.data.database.NoBrokerDataBase
import com.example.nobroker.data.database.NoBrokerDataEntity
import com.example.nobroker.respository.Repository
import com.example.nobroker.utils.MyApplication
import com.example.nobroker.utils.onItemClickListener
import com.example.nobroker.viewmodel.NoBrokerViewModel
import com.example.nobroker.viewmodel.NoBrokerViewModelFactory
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity(), onItemClickListener {

    private lateinit var viewModel: NoBrokerViewModel
    var detailsList: MutableList<NoBrokerDataEntity> = mutableListOf()
    var searchDataList: MutableList<NoBrokerDataEntity> = mutableListOf()
    lateinit var searchAdapter: SearchActivityAdapter
    private lateinit var repository: Repository
    private lateinit var noBrokerDao: NoBrokerDao


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

            /* stops the shimmer effect after data is loaded*/
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
            rvSearchActivity.visibility = View.VISIBLE
            detailsList.clear()
            detailsList.addAll(it)
            searchDataList.addAll(detailsList)
            searchAdapter.notifyDataSetChanged()

        })

    }


    /*
Function which checks the database, before inserting the values into the table.
     */
    private fun checkDataBase() {

        viewModel.checkDataBase().observe(this, Observer {

            if(it == 1){

                addTasksToDataBase()

            }

        })




    }


    /*
Initializing the viewModel and passing he repository object into the view model.
     */
    private fun initializeViewModelAndRepository() {
        repository = (application as MyApplication).repository
        val viewModelFactory = NoBrokerViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NoBrokerViewModel::class.java)

    }


    /*
Function to set the adapter of the recycler view.
     */
    private fun bindRecyclerViewDataAndAdapter() {

        val llManager = LinearLayoutManager(this)
        searchAdapter = SearchActivityAdapter(searchDataList, this)
        rvSearchActivity.layoutManager = llManager
        rvSearchActivity.adapter = searchAdapter
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
                    rvSearchActivity.adapter!!.notifyDataSetChanged()
                } else {

                    searchDataList.clear()
                    searchDataList.addAll(detailsList)
                    rvSearchActivity.adapter!!.notifyDataSetChanged()

                }
                return false
            }
        })

    }

    /*
Function to add data into Database
     */
    private fun addTasksToDataBase() {

        noBrokerDao = NoBrokerDataBase.getNewsArticlesDatabase(this).getNoBrokerDao()

        viewModel.addToDataBase().observe(this, Observer {


            CoroutineScope(Dispatchers.IO).launch {

                for (i in it?.indices!!) {
                    var NoBrokerDataEntity =
                        NoBrokerDataEntity(it?.get(i)?.image,
                            it?.get(i)?.title,
                            it?.get(i)?.subTitle)

                    noBrokerDao.insertData(NoBrokerDataEntity)
                }
            }
        })

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