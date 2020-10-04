package com.example.kotlin.fragments

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kotlin.R
import com.example.kotlin.model.Priority
import com.example.kotlin.model.ToDoData

class SharedViewModel(application: Application):AndroidViewModel(application){

    /** ============================= List Fragment ============================= */

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)
    
    

    fun checkIfDatabaseEmpty(todoData:List<ToDoData>){
        emptyDatabase.value = todoData.isEmpty()
    }
    var listener:AdapterView.OnItemSelectedListener = object :
    AdapterView.OnItemSelectedListener{




        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,
                    R.color.red))}
                1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,
                    R.color.yellow))}
                2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,
                    R.color.green))}
            }
        }
    }

     fun verifyDataFromUser(title:String,description:String):Boolean{
         return !(title.isEmpty() || description.isEmpty())
     }

     fun parsePriority(priority:String): Priority {
        return when(priority){
            "High Priority" -> {
                Priority.HIGH}
            "Medium Priority" -> {
                Priority.MEDIUM}
            "Low Priority" -> {
                Priority.LOW}
            else -> Priority.LOW
        }
    }

}