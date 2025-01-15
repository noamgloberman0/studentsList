package com.example.secondproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.secondproject.model.Model
import com.example.secondproject.model.Student

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
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
        val saveButton: Button = findViewById<Button>(R.id.saveButton)
        val cancelButton: Button = findViewById<Button>(R.id.cancelButton)
        val deleteButton: Button = findViewById<Button>(R.id.deleteButton)
        val errorMessage: TextView = findViewById<TextView>(R.id.errorMessage)
        var nameInput = findViewById<EditText>(R.id.editName)
        var idInput = findViewById<EditText>(R.id.editID)
        var phoneInput = findViewById<EditText>(R.id.editPhone)
        var addressInput = findViewById<EditText>(R.id.editAddress)
        var checkedInput = findViewById<CheckBox>(R.id.editChecked)

        // render current fields
        val currentStudent: Student = Model.shared.students[position]
        nameInput.setText(currentStudent.name)
        idInput.setText(currentStudent.id.toString())
        phoneInput.setText(currentStudent.phone)
        addressInput.setText(currentStudent.address)
        checkedInput.isChecked = currentStudent.checked

        saveButton.setOnClickListener {
            Log.d("Activity", "save button clicked")

            val nameInputValue: String = nameInput.text.toString()
            val idInputValue: String = idInput.text.toString()
            val phoneInputValue: String = phoneInput.text.toString()
            val addressInputValue: String = addressInput.text.toString()
            if (nameInputValue.isEmpty() || idInputValue.isEmpty() || phoneInputValue.isEmpty() || addressInputValue.isEmpty()) {

                errorMessage.text = "Please fill in all fields"

            }
            else {
                errorMessage.text = "Successfully editted $nameInputValue"
                Model.shared.editStudent(position, nameInputValue, idInputValue.toInt(), phoneInputValue, addressInputValue, checkedInput.isChecked)
                val studentsListIntent = Intent(this, StudentsListActivity::class.java)
                this.startActivity(studentsListIntent)
                finish()
            }
        }

        cancelButton.setOnClickListener {
            Log.d("Activity", "cancel button clicked")
            finish()
        }

        deleteButton.setOnClickListener {
            Model.shared.removeStudent(position)
            val studentsListIntent = Intent(this, StudentsListActivity::class.java)
            this.startActivity(studentsListIntent)
            finish()
        }

    }
}