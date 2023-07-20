package com.techydeveloper.memes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techydeveloper.memes.model.MemeModel
import com.techydeveloper.memes.repository.MemeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(val memeRepository: MemeRepository) : ViewModel() {

    var memeMutableLiveData = MutableLiveData<MemeModel>()
    val memeLiveData : LiveData<MemeModel>
    get() = memeRepository.memeMutableLiveData

    fun getRandomMeme(){
        viewModelScope.launch {
            memeRepository.getRandomMeme()
        }
    }
}