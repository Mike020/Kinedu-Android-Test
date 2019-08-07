package com.miguel.kineduandroidtest.model

data class ArticlesResponse(
    val data : ArticlesData
)

data class ArticlesData(

    val articles : List<Articles>
)

data class Articles (
    val name : String?,
    val short_description : String?,
    val picture : String?,
    val id : String?
)