package com.example.secondproject.model

import android.util.Log

class Model private constructor()  {

    var students: MutableList<Student> = ArrayList()
    private val dataChangedListeners: MutableList<() -> Unit> = mutableListOf()

    companion object {

        val shared = Model()

    }

    // Add a listener to notify when the data changes
    fun addDataChangedListener(listener: () -> Unit) {
        dataChangedListeners.add(listener)
    }

    // Notify the listeners when data changes
    private fun notifyDataChanged() {
        for (listener in dataChangedListeners) {
            listener()
        }
    }

    fun addStudent(student: Student) {
        students.add(student)
        notifyDataChanged()
    }

    fun updateStudentChecked(student: Student, position: Int, checked: Boolean) {
        Log.d("Model", "updateStudentChecked called with $position")
        students[position].checked = checked
        Log.d("Activity", students.toString())
        notifyDataChanged()
    }

    fun editStudent(position: Int, nameInput: String, id: Int, phoneInput: String, addressInput: String, checked: Boolean) {

        students[position] = Student(name = nameInput, id = id, phone = phoneInput, address = addressInput, checked = checked)
        notifyDataChanged()

    }

    init {
        students.add(Student("Noam Globerman", 324871466, "0503730945", "bialik 96", true))
    }

}