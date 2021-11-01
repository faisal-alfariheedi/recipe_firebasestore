package com.example.recipe_firebasestore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.recipe_firebasestore.network.recipe
import com.example.recipe_firebasestore.repo

class Vm(application: Application) : AndroidViewModel(application) {
    var rep= repo(application)
    private var list=rep.getAll()

    fun addedit(rec: recipe.dat){
        rep.addedit(rec)
    }
    fun delete(rec: recipe.dat){
        rep.delete(rec)
    }
    fun getAll():LiveData<List<recipe.dat>>{
        return list
    }
    fun addall(rec:List<recipe.dat>){
        rep.addall(rec)
    }

}