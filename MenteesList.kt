package com.example.investor

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Data class to represent a Mentee
data class Mentee(
    val name: String,
    val imageResource: Int,
    val startupName: String
)

class MenteesList : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private var itemList: List<Mentee> = listOf(
        Mentee("Harry Enriquez", R.drawable.mentee1, "ZenithWorks"),
        Mentee("Dayton Glass", R.drawable.mentee2, "Bright Hatch"),
        Mentee("Alex Brown", R.drawable.mentee3, "Quantum Bloom"),
        Mentee("Martha Stark", R.drawable.mentee4, "CloudVibe")
    )
    private var filteredList: List<Mentee> = itemList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentees_list)

        // Enable edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize search view
        searchView = findViewById(R.id.searchView)

        // Initialize Mentee views (cards)
        val groupView1: LinearLayout = findViewById(R.id.menteecard1)
        val groupView2: LinearLayout = findViewById(R.id.menteecard2)
        val groupView3: LinearLayout = findViewById(R.id.menteecard3)
        val groupView4: LinearLayout = findViewById(R.id.menteecard4)

        // Set up search view to handle text input
        setupSearchView()
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })
    }

    private fun filter(query: String?) {
        // Show all mentee cards if the query is empty
        if (query.isNullOrEmpty()) {
            findViewById<LinearLayout>(R.id.menteecard1).visibility = View.VISIBLE
            findViewById<LinearLayout>(R.id.menteecard2).visibility = View.VISIBLE
            findViewById<LinearLayout>(R.id.menteecard3).visibility = View.VISIBLE
            findViewById<LinearLayout>(R.id.menteecard4).visibility = View.VISIBLE
        } else {
            // Filter the list based on the query
            filteredList = itemList.filter {
                it.name.contains(query, ignoreCase = true) || it.startupName.contains(query, ignoreCase = true)
            }

            // Toggle visibility of mentee cards based on the filtering result
            findViewById<LinearLayout>(R.id.menteecard1).visibility =
                if (filteredList.contains(itemList[0])) View.VISIBLE else View.GONE
            findViewById<LinearLayout>(R.id.menteecard2).visibility =
                if (filteredList.contains(itemList[1])) View.VISIBLE else View.GONE
            findViewById<LinearLayout>(R.id.menteecard3).visibility =
                if (filteredList.contains(itemList[2])) View.VISIBLE else View.GONE
            findViewById<LinearLayout>(R.id.menteecard4).visibility =
                if (filteredList.contains(itemList[3])) View.VISIBLE else View.GONE
        }
    }
}
