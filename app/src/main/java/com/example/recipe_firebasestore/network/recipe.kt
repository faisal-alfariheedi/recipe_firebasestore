package com.example.recipe_firebasestore.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class recipe {


    var data: List<dat>? = null

    @Entity(tableName="recipe")
    class dat(

        @ColumnInfo(name="title")
        @SerializedName("title")
        var title: String? = "",

        @ColumnInfo(name="author")
        @SerializedName("author")
        var author: String? = "",

        @ColumnInfo(name="ingredients")
        @SerializedName("ingredients")
        var ingredients: String? = "",

        @ColumnInfo(name="instructions")
        @SerializedName("instructions")
        var instructions: String? = "",

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="id")
        @SerializedName("pk")
        var id: Int = 0
        )

}