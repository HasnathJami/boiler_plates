package com.jsn.msnhope.ui.multiple_view_types

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jsn.msnhope.R
import com.jsn.msnhope.data.local.entities.UserEntity
import com.jsn.msnhope.ui.user.UserViewModel

class MultipleViewTypesActivity : AppCompatActivity() {
    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_view_types)

        viewModel.users.observe(this) {
            populateRecView(it)
        }


    }

    private fun populateRecView(users: List<UserEntity>) {
        val rvMain = findViewById<RecyclerView>(R.id.parentRecView)
        rvMain.layoutManager = LinearLayoutManager(this@MultipleViewTypesActivity)
        val multipleViewTypesAdapter = MultipleViewTypesAdapter(this@MultipleViewTypesActivity, users)
        rvMain.adapter = multipleViewTypesAdapter
    }
}