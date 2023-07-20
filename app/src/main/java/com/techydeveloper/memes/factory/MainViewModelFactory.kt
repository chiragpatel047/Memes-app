package com.techydeveloper.memes.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techydeveloper.memes.repository.MemeRepository
import com.techydeveloper.memes.viewmodel.MainViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(val memeRepository: MemeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(memeRepository) as T
    }
}