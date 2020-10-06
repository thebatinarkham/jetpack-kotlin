package com.example.kotlin.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin.data.ToDoDatabase
import com.example.kotlin.data.api.ToDoApiService
import com.example.kotlin.data.models.ToDoData
import com.example.kotlin.repository.ToDoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoDao = ToDoDatabase.getDatabase(
        application
    ).todoDao()
    private val repository: ToDoRepository

    val getAllData: LiveData<List<ToDoData>>
    val sortByHighPriority: LiveData<List<ToDoData>>
    val sortByLowPriority: LiveData<List<ToDoData>>
    val toDoService = ToDoApiService()
    val disposable = CompositeDisposable()

    init {
        repository = ToDoRepository(toDoDao)
        getAllData = repository.getAllData
        sortByHighPriority = repository.sortByHighPriority
        sortByLowPriority = repository.sortByLowPriority
    }

    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }

    fun updateData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(toDoData)
        }
    }

    fun deleteItem(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(toDoData)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>>{
        return repository.searchDatabase(searchQuery)
    }

    fun fetchFromRemote(){
        viewModelScope.launch(Dispatchers.IO) {
            disposable.add(
                toDoService.getTodo().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<List<ToDoData>>(){
                        override fun onSuccess(toDoData: List<ToDoData>) {
                            toDoData.forEach{
                                insertData(it)
                            }
                            Toast.makeText(getApplication(),"Todo Retrieved From End Point.",Toast.LENGTH_LONG).show()
                        }
                        override fun onError(e: Throwable) {
                            Toast.makeText(getApplication(),"Something wrong happen: ${e.localizedMessage}",Toast.LENGTH_LONG).show()
                        }

                    })
            )

        }

    }




}