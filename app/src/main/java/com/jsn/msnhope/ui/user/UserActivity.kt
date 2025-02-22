package com.jsn.msnhope.ui.user

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.jsn.msnhope.data.local.entities.UserEntity
import com.jsn.msnhope.databinding.ActivityUserBinding
import com.jsn.msnhope.ui.MainActivity

class UserActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()
    private lateinit var adapter: UserListAdapter
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_user)
        setContentView(binding.root)

        subscribeUiToRecyclerView()

        handleClickEvents()

    }

    private fun handleClickEvents() {
        binding.fabAdd.setOnClickListener{
            handleFabButtonClick() {
                Toast.makeText(this@UserActivity, "Saved", Toast.LENGTH_LONG).show()
//                val intent = Intent(this@UserActivity, MainActivity::class.java)
//                startActivity(intent)
            }
        }
    }

    private fun handleFabButtonClick(onClick: () -> Unit)
    {
        viewModel.insertUser(
            UserEntity(
                userId = "1",
                userName = "Jami",
                userAge = "122",
                description = "3333dfdf ddfjdjf jjddjjfd jdjfjdjf  jdjfjdf"
            )
        )
        onClick()
    }

    private fun subscribeUiToRecyclerView() {
        viewModel.users.observe(this) { users ->
            populateRecView(users)
        }
    }


    private fun populateRecView(users: List<UserEntity>) {
        adapter = UserListAdapter(users)
//        binding.recViewUsers.layoutManager = LinearLayoutManager(this)
//        binding.recViewUsers.layoutManager = GridLayoutManager(this, 2)
        binding.recViewUsers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.recViewUsers.adapter = adapter
    }


}