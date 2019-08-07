package com.miguel.kineduandroidtest.model

data class ActivitiesResponse(
    val data : ActivitiesData
)

data class ActivitiesData(

    val activities : List<Activities>
)
data class  Activities(
    val name : String?,
    val purpose : String?,
    val thumbnail : String?
)

