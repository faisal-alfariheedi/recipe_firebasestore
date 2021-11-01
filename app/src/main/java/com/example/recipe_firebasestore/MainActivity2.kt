package com.example.recipe_firebasestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe_firebasestore.network.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity2 : AppCompatActivity() {
    lateinit var rv: RecyclerView
    lateinit var but: Button
    lateinit var apif: APIInterface
    var res: ArrayList<recipe.dat> = arrayListOf()
    lateinit var prog: ProgressBar
    lateinit var tvw: TextView
    lateinit var db: RecipeDao
    val fdb = Firebase.firestore
    val mvm by lazy { ViewModelProvider(this).get(Vm::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        init()
        but.setOnClickListener{
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)

        }
        CoroutineScope(Dispatchers.IO).launch {
            fdb.collection("menu").get()
                .addOnSuccessListener { result ->
                    var flist = result.documents
                    var clist = mutableListOf<recipe.dat>()
                    for (note in flist.toList()) {
                        clist.add(note.toObject<recipe.dat>()!!)
                    }
                    CoroutineScope(Dispatchers.IO).launch {db.delall()
                    mvm.addall(clist)
                    runOnUiThread{wait(false)
                    Toast.makeText(applicationContext, "updated", Toast.LENGTH_SHORT).show()}}
                }
                .addOnFailureListener {
                    runOnUiThread{Toast.makeText(applicationContext, "fail", Toast.LENGTH_SHORT).show()}
                }
        }


    }
    fun init() {
        db= RecipeDB.getInstance(this).RecipeDao()
        but = findViewById(R.id.addres)
        rv = findViewById(R.id.rvma)
        rv.layoutManager = LinearLayoutManager(this)
        var ad= RVAdapter(this)
        rv.adapter = ad
        mvm.getAll().observe(this,{
            ad.setrv(it)
            Toast.makeText(this,"updated",Toast.LENGTH_SHORT).show()
        })
        apif = APIClient().getClient()?.create(APIInterface::class.java)!!
        tvw=findViewById(R.id.wait)
        prog=findViewById(R.id.progressBar)
        wait(true)
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