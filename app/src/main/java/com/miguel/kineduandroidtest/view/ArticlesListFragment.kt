package com.miguel.kineduandroidtest.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.miguel.kineduandroidtest.R
import com.miguel.kineduandroidtest.model.Articles
import com.miguel.kineduandroidtest.viewmodel.ArticlesListViewModel
import kotlinx.android.synthetic.main.article_item.*
import kotlinx.android.synthetic.main.fragment_articles_list.*
import kotlinx.android.synthetic.main.fragment_articles_list.listError
import kotlinx.android.synthetic.main.fragment_articles_list.loadingView


class ArticlesListFragment : Fragment() {

    private lateinit var viewModel : ArticlesListViewModel
    private val listAdapter = ArticlesAdapter (arrayListOf())

    private val articlesListDataObserver = Observer<List<Articles>>{ list ->
        list?.let {
            articlesList.visibility = View.VISIBLE
            listAdapter.updateActivitiesList(it)
        }
    }

    private val loadingliveDataObserver = Observer<Boolean> { isLoading ->
        loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
        if (isLoading){
            listError.visibility = View.GONE
            articlesList.visibility = View.GONE
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
        return inflater.inflate(R.layout.fragment_articles_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ArticlesListViewModel::class.java)
        viewModel.articles.observe(this,articlesListDataObserver)
        viewModel.loading.observe(this, loadingliveDataObserver)
        viewModel.loadError.observe(this, errorLiveDataObserver)
        viewModel.refresh()

        articlesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        refreshLayout.setOnRefreshListener {
            articlesList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false

        }
    }


}
