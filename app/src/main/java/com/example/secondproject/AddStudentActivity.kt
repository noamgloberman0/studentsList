package com.example.secondproject

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
import org.w3c.dom.Text

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
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

        val saveButton: Button = findViewById<Button>(R.id.saveButton)
        val errorMessage: TextView = findViewById<TextView>(R.id.errorMessage)

        saveButton.setOnClickListener {
            Log.d("Activity", "save button clicked")
            val nameInput: String = (findViewById<EditText>(R.id.editName)).text.toString()
            val idInput: String = (findViewById<EditText>(R.id.editID)).text.toString()
            val phoneInput: String = (findViewById<EditText>(R.id.editPhone)).text.toString()
            val addressInput: String = (findViewById<EditText>(R.id.editAddress)).text.toString()
            val checkedInput: String = (findViewById<CheckBox>(R.id.editChecked)).isChecked.toString()

            if (nameInput.isEmpty() || idInput.isEmpty() || phoneInput.isEmpty() || addressInput.isEmpty()) {

                errorMessage.text = "Please fill in all fields"

            }
            else {

                errorMessage.text = "Successfully added $nameInput"
                Model.shared.addStudent(Student(nameInput, idInput.toInt(), phoneInput, addressInput, checkedInput.toBoolean()))

                Log.d("Activity", Model.shared.students.toString())
                finish()
            }
        }

        val cancelButton: Button = findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            Log.d("Activity", "cancel button clicked")
            finish()
        }

    }
}