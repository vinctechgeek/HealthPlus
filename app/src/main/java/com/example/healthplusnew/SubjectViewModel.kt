package com.example.healthplusnew

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class SubjectViewModel(application: Application) : AndroidViewModel(application) {
    private val cRepository: SubjectRepository

    init {
        cRepository = SubjectRepository(application)
    }

    val allSubjects: LiveData<List<Subject>> = cRepository.allSubjects.asLiveData()
    fun insertSubject(subject: Subject) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.insert(subject)
    }

    fun deleteSubject(subject: Subject) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.delete(subject)
    }
}