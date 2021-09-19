package dev.muthuram.nasapictures.helper

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


fun <T> LiveData<T>.observeLiveData(lifecycleOwner: LifecycleOwner, function: (T) -> Unit) {
    this.observe(lifecycleOwner, {
        if (it != null)
            function.invoke(it)
    })
}


inline fun <Y, X> ViewModel.coMap(
    source: LiveData<X>,
    triggerOnNull: Boolean = true,
    crossinline map: suspend (X) -> Y?
): LiveData<Y> {
    val result = MediatorLiveData<Y>()
    var job: Job? = null
    result.addSource(source) { input ->
        //if triggerOnNull is false the input should not be null
        if (triggerOnNull || input != null) {
            job = viewModelScope.launch(Dispatchers.IO) {
                result.postValue(map(input))
            }
        }
    }
    return result
}