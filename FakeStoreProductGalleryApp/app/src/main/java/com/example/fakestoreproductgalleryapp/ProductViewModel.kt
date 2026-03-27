package com.example.fakestoreproductgalleryapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed interface ProductUiState {
    data class Success(val products: List<Product>) : ProductUiState
    object Error : ProductUiState
    object Loading : ProductUiState
}

class ProductViewModel : ViewModel() {

    var productUiState: ProductUiState by mutableStateOf(ProductUiState.Loading)
        private set

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            productUiState = try {
                val result = ProductApi.retrofitService.getProducts()
                ProductUiState.Success(result)
            } catch (e: Exception) {
                ProductUiState.Error
            }
        }
    }
}