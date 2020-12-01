package com.devkanhaiya.newsapp

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdapter(
    private var titles: List<String>,
    private var author: List<String>,
    private var url: List<String>,
    private var imageurl: List<String>
):RecyclerView.Adapter<CustomAdapter.NewsAdapter>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        val viewHolder =  NewsAdapter(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsAdapter, position: Int) {
        holder.titleView.text=titles[position]
        holder.authorView.text=author[position]
        Glide.with(holder.imageview)
                .load(imageurl[position])
                .into(holder.imageview)
    }

    override fun getItemCount(): Int {
       return titles.size
    }


   inner class NewsAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleView: TextView = itemView.findViewById(R.id.titles)
        val authorView: TextView = itemView.findViewById(R.id.author)
       val imageview: ImageView = itemView.findViewById(R.id.image_view)
       init {
            itemView.setOnClickListener{v : View ->
                val position: Int = adapterPosition

                val intent = Intent(Intent.ACTION_VIEW)

                intent.data = Uri.parse(url[position])
                startActivity(itemView.context,intent,null)

            }
        }
    }

}

