package es.shiro.pokedex

sealed class UiState {
    object Loading: UiState()
    object ShowContent: UiState()
    object Error: UiState()
}