package com.example.investor


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.LinearLayout


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
        enableEdgeToEdge()
        setContentView(R.layout.activity_mentees_list)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        searchView = findViewById(R.id.searchView)

        val groupView1: LinearLayout = findViewById(R.id.menteecard1)
        val groupView2: LinearLayout = findViewById(R.id.menteecard2)
        val groupView3: LinearLayout = findViewById(R.id.menteecard3)
        val groupView4: LinearLayout = findViewById(R.id.menteecard4)

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
        filteredList = if (query.isNullOrEmpty()) {
            itemList
        } else {
            itemList.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.startupName.contains(query, ignoreCase = true)
            }
        }

    }
}

