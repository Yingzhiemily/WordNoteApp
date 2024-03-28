package com.example.wordnote.feature.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordnote.model.WordResult
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    private val _searchResults = MutableLiveData<List<WordResult>>()
    val searchResults: LiveData<List<WordResult>> = _searchResults

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    sealed class ViewState {
        object Initial : ViewState()
        object Loading : ViewState()
        object Error : ViewState()
        object NoResults : ViewState()
    }

    fun searchWord(word: String) {
        viewModelScope.launch {
            _viewState.value = ViewState.Loading
            try {
                val response = repository.getWord(word)
                if (response.isNotEmpty()) {
                    _searchResults.value = response
                    _viewState.value = ViewState.Initial
                } else {
                    _viewState.value = ViewState.NoResults
                }
            } catch (e: Exception) {
                _viewState.value = ViewState.Error
                Log.e("SearchViewModel", "error: $e")
            }
        }
    }
}
