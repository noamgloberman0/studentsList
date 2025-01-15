package com.example.secondproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.secondproject.model.Model

class StudentsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize RecyclerView
        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up the Adapter
        val studentAdapter = StudentAdapter(Model.shared.students)
        recyclerView.adapter = studentAdapter

        // Register a listener to update the RecyclerView whenever the data changes
        Model.shared.addDataChangedListener {
            studentAdapter.notifyDataSetChanged()  // Refresh the RecyclerView
        }

        val addStudentButton: Button = findViewById<Button>(R.id.AddStudentButton)
        addStudentButton.setOnClickListener {
            Log.d("Activity", "AddStudentButton clicked")
            val addStudentIntent = Intent(this, AddStudentActivity::class.java)
            startActivity(addStudentIntent)
        }

        val backButton: ImageButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            Log.d("Activity", "back button clicked")
            finish()
        }

        Log.d("Activity", Model.shared.students.toString())

    }

}