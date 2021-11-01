package com.example.recipe_firebasestore

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_firebasestore.network.recipe

class RVAdapter( val cont: Context): RecyclerView.Adapter<RVAdapter.ItemViewHolder>()  {
    var rv= listOf<recipe.dat>()
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rvlist,parent,false )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val rvv = rv[position].title
        val rvvd= rv[position].author
        holder.itemView.apply {
            var rvlisting= findViewById<CardView>(R.id.rvlisting)
            var ct= findViewById<TextView>(R.id.cardtitle)
            var cd= findViewById<TextView>(R.id.carddesc)
            ct.text = rvv.toString()
            cd.text = rvvd.toString()
            rvlisting.setOnClickListener {
                var intent = Intent(cont, view::class.java)
                intent.putExtra("tit",rv[position].id)
                context.startActivity(intent)
            }
        }
    }
    fun setrv(n:List<recipe.dat>){
        rv=n
        notifyDataSetChanged()
    }

    override fun getItemCount() = rv.size
}