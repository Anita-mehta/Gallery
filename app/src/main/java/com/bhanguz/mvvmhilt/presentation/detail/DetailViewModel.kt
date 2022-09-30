package com.bhanguz.mvvmhilt.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhanguz.mvvmhilt.data.local.ImageEntity
import com.bhanguz.mvvmhilt.data.repository.ImageRepository
import com.bhanguz.mvvmhilt.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: ImageRepository) : ViewModel() {


    private var _images: MutableSharedFlow<Resources<List<ImageEntity>?>> = MutableSharedFlow()

    var img: SharedFlow<Resources<List<ImageEntity>?>> = _images

    init {
        getImages()
    }
    private fun getImages() {
        viewModelScope.launch { _images.emit(repository.getImage()) }
    }

}