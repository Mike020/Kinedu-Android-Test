package com.miguel.kineduandroidtest.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.miguel.kineduandroidtest.R
import com.miguel.kineduandroidtest.model.Articles
import com.miguel.kineduandroidtest.util.getProgressDrawable
import com.miguel.kineduandroidtest.util.loadImage
import kotlinx.android.synthetic.main.article_item.view.*

class ArticlesAdapter(private val articlesList : ArrayList<Articles>) :
    RecyclerView.Adapter<ArticlesAdapter.ArticlesViewholder > ()  {


    fun updateActivitiesList (newActivitieslList : List<Articles>){
        articlesList.clear()
        articlesList.addAll(newActivitieslList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewholder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.article_item , parent , false)
        return ArticlesViewholder(view)
    }

    override fun getItemCount() = articlesList.count()

    override fun onBindViewHolder(holder: ArticlesViewholder, position: Int) {

        articlesList[position].id?.let {
            holder.bindId(it)
        }

        holder.view.nameTextView.text = articlesList[position].name
        holder.view.detailTextView.text = articlesList[position].short_description
        holder.view.articleImage.loadImage(articlesList[position].picture,
            getProgressDrawable(holder.view.context)
        )
    }


    class ArticlesViewholder (var view : View) : RecyclerView.ViewHolder(view) , View.OnClickListener {

        var id = ""

        init {
            view.setOnClickListener(this)
        }

        fun bindId (id : String){
            this.id = id
        }

        override fun onClick(p0: View?) {
            val intent = Intent(view.context , ArticleDetailActivity::class.java)
            intent.putExtra("articleId" , id )
            startActivity(view.context,intent , null)


        }
    }
}