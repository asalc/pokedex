package es.shiro.pokedex

sealed class UiState {
    object Loading: UiState()
    object ShowContent: UiState()
    class Error(val errorResource: Int): UiState()
}