package com.example.recipe_firebasestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import com.example.recipe_firebasestore.network.APIClient
import com.example.recipe_firebasestore.network.APIInterface
import com.example.recipe_firebasestore.network.recipe
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var intitle: EditText
    lateinit var inauther:EditText
    lateinit var ingrediant:EditText
    lateinit var instraction:EditText
    lateinit var save: Button
    lateinit var view:Button
    lateinit var prog: ProgressBar
    lateinit var tvw: TextView
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        save.setOnClickListener {
            addresp()
        }
        view.setOnClickListener {
            viewrescp()

        }

    }
    fun init() {
        intitle=findViewById(R.id.intit)
        inauther=findViewById(R.id.inauth)
        ingrediant=findViewById(R.id.iing)
        instraction=findViewById(R.id.inins)
        save=findViewById(R.id.savebut)
        view=findViewById(R.id.viewbut)
        tvw=findViewById(R.id.wait)
        prog=findViewById(R.id.progressBar)
    }

    fun addresp() {
        wait(true)
        if (intitle.text.isNotEmpty() && inauther.text.isNotEmpty() && ingrediant.text.isNotEmpty() && instraction.text.isNotEmpty()) {
            var f = recipe.dat(
                intitle.text.toString(),
                inauther.text.toString(),
                ingrediant.text.toString(),
                instraction.text.toString())
            CoroutineScope(Dispatchers.IO).launch {
                db.collection("menu").document(f.title!!).set(f)
                    .addOnSuccessListener { runOnUiThread{wait(false)
                        intitle.setText("")
                        inauther.setText("")
                        ingrediant.setText("")
                        instraction.setText("")
                        Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_LONG).show()} }
                    .addOnFailureListener {runOnUiThread{Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show()
                        wait(false)} }
            }


        } else {
            Toast.makeText(this, "you can`t submit an empty field", Toast.LENGTH_LONG).show()
        }

    }


    fun viewrescp() {
        intent = Intent(applicationContext, MainActivity2::class.java)
        startActivity(intent)
    }
    fun wait(a:Boolean){
        if(a){
            prog.isVisible = true
            tvw.isVisible = true
        }else{
            prog.isVisible=false
            tvw.isVisible=false
        }

    }
}