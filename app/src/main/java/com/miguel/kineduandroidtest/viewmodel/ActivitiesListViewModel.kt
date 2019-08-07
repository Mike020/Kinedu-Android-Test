package com.miguel.kineduandroidtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.miguel.kineduandroidtest.model.Activities
import com.miguel.kineduandroidtest.model.KineduService
import com.miguel.kineduandroidtest.model.ActivitiesResponse
import com.miguel.kineduandroidtest.util.KINEDU_URL_TOKEN
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ActivitiesListViewModel (application: Application) : AndroidViewModel (application) {


    val activities by lazy { MutableLiveData<List<Activities>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }


    private val disposable = CompositeDisposable()
    private val apiService = KineduService()

    private val token = KINEDU_URL_TOKEN

    fun refresh (){
        loading.value = true
        getActivitiesList()
    }

    private fun getActivitiesList(){

        disposable.add(
            apiService.getActivities(token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ActivitiesResponse>(){
                    override fun onSuccess(response: ActivitiesResponse) {

                        

                        loadError.value = false
                        loading.value = false
                        activities.value = response.data.activities
                    }



                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        activities.value = null
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