package com.techydeveloper.memes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.techydeveloper.memes.api.MemeApi
import com.techydeveloper.memes.model.MemeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MemeRepository @Inject constructor(val memeApi: MemeApi) {

    var memeMutableLiveData = MutableLiveData<MemeModel>()
    val memeLiveData : LiveData<MemeModel>
        get() = memeMutableLiveData

    suspend fun getRandomMeme(){
        CoroutineScope(Dispatchers.IO).launch {
            val request = memeApi.getRandomMeme()

            if(request.isSuccessful && request.body()!=null){
                memeMutableLiveData.postValue(request.body()!!)
            }
        }
    }
}