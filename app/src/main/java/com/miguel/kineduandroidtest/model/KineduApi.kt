package com.miguel.kineduandroidtest.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface KineduApi {

    @GET("catalogue/activities?skill_id=5&baby_id=2064732")
    fun getActivitiesList(@Header("Authorization") Token : String): Single <ActivitiesResponse>

    @GET ("catalogue/articles?skill_id=5&baby_id=2064732")
    fun getArticlesList(@Header("Authorization") Token: String) : Single<ArticlesResponse>

    @GET("articles/{id}")
    fun getArticleDetail(@Header("Authorization") Token: String , @Path ("id") articleId : String ) : Single<ArticleDetailResponse>

}