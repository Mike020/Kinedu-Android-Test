package com.miguel.kineduandroidtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.miguel.kineduandroidtest.model.Articles
import com.miguel.kineduandroidtest.model.ArticlesResponse
import com.miguel.kineduandroidtest.model.KineduService
import com.miguel.kineduandroidtest.util.KINEDU_URL_TOKEN
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ArticlesListViewModel (application: Application) : AndroidViewModel (application) {

    val articles by lazy { MutableLiveData<List<Articles>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }


    private val disposable = CompositeDisposable()
    private val apiService = KineduService()

    private val token = KINEDU_URL_TOKEN


    fun refresh (){
        loading.value = true
        getArticlesList()
    }

    private fun getArticlesList(){

        disposable.add(
            apiService.getArticles(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ArticlesResponse>(){
                    override fun onSuccess(response: ArticlesResponse) {



                        loadError.value = false
                        loading.value = false
                        articles.value = response.data.articles
                    }



                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        articles.value = null
                        loadError.value = true

                    }


                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}