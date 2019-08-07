package com.miguel.kineduandroidtest.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class KineduService {

    val baseUrl = "http://staging.kinedu.com/api/v3/"

    private val api = Retrofit.Builder().
        baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(KineduApi::class.java)


    fun getActivities (Token : String ) : Single<ActivitiesResponse> {
        return api.getActivitiesList(Token)
    }

    fun getArticles (Token: String) : Single<ArticlesResponse>{
        return api.getArticlesList(Token)
    }

    fun getArticleDetail (Token: String , id : String) : Single<ArticleDetailResponse>{
        return api.getArticleDetail(Token , id)
    }

}