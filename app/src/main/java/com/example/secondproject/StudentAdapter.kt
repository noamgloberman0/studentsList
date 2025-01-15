package com.example.secondproject

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.secondproject.model.Model
import com.example.secondproject.model.Student

class StudentAdapter(private val students: List<Student>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student, position)
    }

    override fun getItemCount(): Int = students.size

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileImageView: ImageView = itemView.findViewById(R.id.studentProfileImage)
        private val nameTextView: TextView = itemView.findViewById(R.id.studentName)
        private val idTextView: TextView = itemView.findViewById(R.id.studentId)
        private val checkBox: CheckBox = itemView.findViewById(R.id.studentCheckbox)

        fun bind(student: Student, position: Int) {
            // Bind student details
            nameTextView.text = student.name
            idTextView.text = "ID: ${student.id}"

            // Set checkbox state
            checkBox.isChecked = student.checked
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                Model.shared.updateStudentChecked(student, position, isChecked)
            }

            itemView.setOnClickListener {

                Log.d("StudentAdapter","Item clicked at position: $position")
                var studentDetailsIntent = Intent(itemView.context, StudentDetailsActivity::class.java)
                studentDetailsIntent.putExtra("studentPosition", position)
                itemView.context.startActivity(studentDetailsIntent)

            }
        }
    }
}
