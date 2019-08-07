package com.miguel.kineduandroidtest.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miguel.kineduandroidtest.R
import com.miguel.kineduandroidtest.model.Activities
import com.miguel.kineduandroidtest.util.getProgressDrawable
import com.miguel.kineduandroidtest.util.loadImage
import kotlinx.android.synthetic.main.activities_item.view.*

class ActivitesAdapter (private val activitesList : ArrayList<Activities>) :
    RecyclerView.Adapter<ActivitesAdapter.ActivitiesViewholder> (){


    fun updateActivitiesList (newActivitieslList : List<Activities>){
        activitesList.clear()
        activitesList.addAll(newActivitieslList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesViewholder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.activities_item , parent , false)
        return ActivitiesViewholder(view)
    }

    override fun getItemCount() =  activitesList.size


    override fun onBindViewHolder(holder: ActivitiesViewholder, position: Int) {
        holder.view.nameTextView.text = activitesList[position].name
        holder.view.detailTextView.text = activitesList[position].purpose
        holder.view.activityImage.loadImage(activitesList[position].thumbnail,
            getProgressDrawable(holder.view.context))
    }


    class ActivitiesViewholder (var view : View) : RecyclerView.ViewHolder(view)
}