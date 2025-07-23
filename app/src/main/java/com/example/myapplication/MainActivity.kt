package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.PetAdapter
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private val petList = mutableListOf<Pet>()
    private lateinit var rvPets: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvPets = findViewById(R.id.rvPets)
        fetchDogImage()
    }

    private fun fetchDogImage() {
        val client = AsyncHttpClient()
        client.get("https://dog.ceo/api/breeds/image/random/10", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val images = json.jsonObject.getJSONArray("message")
                for (i in 0 until images.length()) {
                    val imageUrl = images.getString(i)
                    val name = "Doggo $i"
                    val fact = dogFacts.random()

                    val pet = Pet(imageUrl, name, fact)
                    petList.add(pet)
                }

                val adapter = PetAdapter(petList)
                rvPets.adapter = adapter
                rvPets.layoutManager = LinearLayoutManager(this@MainActivity)
                rvPets.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
            }

            override fun onFailure(statusCode: Int, headers: Headers?, response: String, throwable: Throwable?) {
                Log.e("Dog API", "Error: $response")
            }
        })
    }

    private val dogFacts = listOf(
        "Loves belly rubs",
        "Chased 3 squirrels this morning",
        "Can bark in 3 languages",
        "Certified couch potato",
        "Terrified of vacuum cleaners",
        "Has 12 different squeaky toys",
        "Once ate homework (for real)",
        "Barks when you say 'cheese'"
    )
}




