package es.shiro.pokedex.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import es.shiro.domain.model.GenericId
import es.shiro.pokedex.R

@Composable
fun <T> PokedexGrid(
    items: List<T>,
    modifier: Modifier = Modifier,
    content: @Composable LazyGridScope.(T) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            top = dimensionResource(R.dimen.padding_regular),
            start = dimensionResource(R.dimen.padding_regular),
            end = dimensionResource(R.dimen.padding_regular),
            bottom = dimensionResource(R.dimen.padding_bottom_grid)
        ),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.padding_mid_small)
        ),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.padding_mid_small)
        ),
        modifier = modifier
    ) {
        items(items = items, key = { (it as GenericId).id }) { item ->
            content.invoke(this@LazyVerticalGrid, item)
        }
    }
}