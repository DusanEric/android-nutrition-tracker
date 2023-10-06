package rs.raf.vezbe11.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    val isLoading = MutableLiveData(true)

    fun SplashViewModel() {
        try {
            Thread.sleep(3000)
            isLoading.setValue(false)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }

}