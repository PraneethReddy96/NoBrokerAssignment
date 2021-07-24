package com.example.nobroker.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class SearchActivity : AppCompatActivity(),onItemClickListener {

    lateinit var viewModel : NoBrokerViewModel
    var detailsList :MutableList<NoBrokerDataEntity>  = mutableListOf()
    lateinit var searchAdapter : SearchActivityAdapter
    lateinit var repository: Repository
    lateinit var noBrokerDao: NoBrokerDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        noBrokerDao = NoBrokerDataBase.getNewsArticlesDatabase(this).getNoBrokerDao()
        repository = (application as MyApplication).repository
        val viewModelFactory = NoBrokerViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NoBrokerViewModel::class.java)



       if( viewModel.checkDataBase() == 0){

//           addTasksToDataBase()

       }

        val llManager = LinearLayoutManager(this)
        searchAdapter = SearchActivityAdapter(detailsList,this)
        rvSearchActivity.layoutManager =llManager
        rvSearchActivity.adapter =searchAdapter



        viewModel.retrieveNoBrokerDataEntity().observe(this, Observer {

            detailsList.clear()
            detailsList.addAll(it)
            searchAdapter.notifyDataSetChanged()

        })
    }

    private fun addTasksToDataBase() {

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

    override fun onItemClicked(entity: NoBrokerDataEntity) {

        val intent = Intent(this,PreviewActivity::class.java)
        intent.putExtra("image",entity.image.toString())
        intent.putExtra("title",entity.title.toString())
        intent.putExtra("subTitle",entity.subTitle.toString())
        startActivity(intent)

    }


}