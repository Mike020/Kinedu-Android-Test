package com.miguel.kineduandroidtest.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.miguel.kineduandroidtest.R
import com.miguel.kineduandroidtest.model.Activities
import com.miguel.kineduandroidtest.viewmodel.ActivitiesListViewModel
import kotlinx.android.synthetic.main.fragment_activites_list.*


class ActivitesListFragment : Fragment() {


    private lateinit var viewModel : ActivitiesListViewModel
    private val listAdapter = ActivitesAdapter(arrayListOf())

    private val activitiesListDataObserver = Observer<List<Activities>>{ list ->
        list?.let {
            activitiesList.visibility = View.VISIBLE
            listAdapter.updateActivitiesList(it)
        }
    }

    private val loadingliveDataObserver = Observer<Boolean> { isLoading ->
        loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
        if (isLoading){
            listError.visibility = View.GONE
            activitiesList.visibility = View.GONE
        }
    }

    private val errorLiveDataObserver = Observer<Boolean> { isError ->
        listError.visibility = if (isError) View.VISIBLE else View.GONE

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activites_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ActivitiesListViewModel::class.java)
        viewModel.activities.observe(this,activitiesListDataObserver)
        viewModel.loading.observe(this, loadingliveDataObserver)
        viewModel.loadError.observe(this, errorLiveDataObserver)
        viewModel.refresh()

        activitiesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        refreshLayout.setOnRefreshListener {
            activitiesList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false

        }
    }

}
