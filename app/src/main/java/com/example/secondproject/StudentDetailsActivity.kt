package com.example.secondproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.secondproject.model.Model

class StudentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backButton: ImageButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            Log.d("Activity", "back button clicked")
            finish()
        }

        val position: Int = intent.getIntExtra("studentPosition", -1)
        val editName: TextView = findViewById<TextView>(R.id.editName)
        val editID: TextView = findViewById<TextView>(R.id.editID)
        val editPhone: TextView = findViewById<TextView>(R.id.editPhone)
        val editAddress: TextView = findViewById<TextView>(R.id.editAddress)
        val editChecked: CheckBox = findViewById<CheckBox>(R.id.editChecked)
        val editButton: Button = findViewById<Button>(R.id.editButton)

        editName.text = Model.shared.students[position].name
        editID.text = Model.shared.students[position].id.toString()
        editPhone.text = Model.shared.students[position].phone
        editAddress.text = Model.shared.students[position].address
        editChecked.isChecked = Model.shared.students[position].checked

        editButton.setOnClickListener {

            Log.d("Activity", "edit button clicked")
            val editStudentIntent = Intent(this, EditStudentActivity::class.java)
            editStudentIntent.putExtra("studentPosition", position)
            this.startActivity(editStudentIntent)
            finish()

        }

    }
}