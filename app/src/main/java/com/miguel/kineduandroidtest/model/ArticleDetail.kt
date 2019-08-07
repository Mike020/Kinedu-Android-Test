package com.miguel.kineduandroidtest.model

data class ArticleDetailResponse (
    val data : ArticleMenu
)

data class ArticleMenu (
    val article : ArticleDetail
)

data class ArticleDetail (
    val picture : String?,
    val body : String?,
    val title : String?,
    val link : String?

)