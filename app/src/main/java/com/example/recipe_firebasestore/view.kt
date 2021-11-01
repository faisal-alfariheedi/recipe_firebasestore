package com.example.recipe_firebasestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.recipe_firebasestore.R
import com.example.recipe_firebasestore.network.RecipeDB
import com.example.recipe_firebasestore.network.RecipeDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class view : AppCompatActivity() {
    lateinit var back: Button
    lateinit var tvtit: TextView
    lateinit var tving:TextView
    lateinit var tvins:TextView
    lateinit var db: RecipeDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        init()
        CoroutineScope(Dispatchers.IO).launch{
            var temp=db.getreci(intent.getIntExtra("tit",0))

        tvtit.text=temp.title
        tving.text=temp.ingredients
        tvins.text=temp.instructions
        }
        back.setOnClickListener{
            onBackPressed()

        }

    }
    fun init(){
        db= RecipeDB.getInstance(this).RecipeDao()
        back=findViewById(R.id.returnbut)
        tvtit=findViewById(R.id.tvtitle)
        tving=findViewById(R.id.tving)
        tvins=findViewById(R.id.tvins)
    }
}