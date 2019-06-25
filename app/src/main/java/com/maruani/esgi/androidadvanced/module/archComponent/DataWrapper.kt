package com.maruani.esgi.androidadvanced.module.archComponent

import androidx.lifecycle.MutableLiveData

sealed class DataWrapper<T>

data class Loading<T>(val loading: Boolean) : DataWrapper<T>()
data class Success<T>(val data: T) : DataWrapper<T>()
data class Failure<T>(val throwable: Throwable) : DataWrapper<T>()

fun <T> MutableLiveData<DataWrapper<T>>.setLoadingState(loading: Boolean) {
    this.value = Loading(loading)
}