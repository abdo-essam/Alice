package com.ae.alice.presentation.screens.location

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.ae.alice.designsystem.components.appBar.AppBar
import com.ae.alice.designsystem.components.button.PrimaryButton
import com.ae.alice.designsystem.components.scaffold.Scaffold
import com.ae.alice.designsystem.theme.Theme
import io.github.dellisd.spatialk.geojson.Position
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import org.koin.compose.viewmodel.koinViewModel
import org.maplibre.compose.camera.CameraPosition
import org.maplibre.compose.camera.rememberCameraState
import org.maplibre.compose.map.GestureOptions
import org.maplibre.compose.map.MapOptions
import org.maplibre.compose.map.MaplibreMap
import org.maplibre.compose.map.OrnamentOptions
import org.maplibre.compose.map.RenderOptions
import org.maplibre.compose.style.BaseStyle
import org.maplibre.compose.util.ClickResult

/**
 * Pick location screen with a full map.
 */
@Composable
fun PickLocationScreen(
    onBackClick: () -> Unit,
    onLocationSubmitted: (Double, Double, String) -> Unit,
    viewModel: LocationViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is LocationEffect.NavigateBack -> onBackClick()
                is LocationEffect.SubmitLocation -> onLocationSubmitted(
                    effect.latitude,
                    effect.longitude,
                    effect.address
                )
            }
        }
    }

    Scaffold(
        backgroundColor = Theme.colorScheme.background.surfaceLow,
        topBar = {
            AppBar(
                title = "تحديد الموقع",
                titleColor = Theme.colorScheme.shadePrimary,
                leadingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "رجوع",
                        tint = Theme.colorScheme.primary.primary
                    )
                },
                onLeadingClick = { viewModel.processIntent(LocationIntent.BackClick) },
            )
        }
    ) {
        val camera = rememberCameraState(
            firstPosition = CameraPosition(
                target = Position(longitude = state.longitude, latitude = state.latitude),
                zoom = 14.0
            )
        )

        LaunchedEffect(camera) {
            snapshotFlow { camera.isCameraMoving }
                .distinctUntilChanged()
                .filter { !it }
                .drop(1)
                .collect {
                    val target = camera.position.target
                    viewModel.processIntent(LocationIntent.OnCameraMove(target.latitude, target.longitude))
                }
        }

        LaunchedEffect(state.latitude, state.longitude) {
            // Animate map when clicking MyLocation or passing coordinates
            val currentTarget = camera.position.target
            if (currentTarget.latitude != state.latitude || currentTarget.longitude != state.longitude) {
                camera.animateTo(
                    finalPosition = camera.position.copy(
                        target = Position(state.longitude, state.latitude),
                        zoom = 16.0
                    )
                )
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            MaplibreMap(
                modifier = Modifier.fillMaxSize(),
                cameraState = camera,
                baseStyle = BaseStyle.Uri("https://tiles.openfreemap.org/styles/bright"),
                options = MapOptions(
                    gestureOptions = GestureOptions.Standard,
                    ornamentOptions = OrnamentOptions.AllDisabled,
                    renderOptions = RenderOptions.Standard
                ),
                onMapClick = { position, _ ->
                    viewModel.processIntent(LocationIntent.OnCameraMove(position.latitude, position.longitude))
                    ClickResult.Pass
                }
            )

            // Center Pin Marker
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 54.dp) // Lift it so pin point is at center
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Theme.colorScheme.error,
                    modifier = Modifier.size(54.dp)
                )
            }

            // Bottom Actions (GPS FAB + Confirm Button)
            Column(
                Modifier
                    .padding(Theme.spacing._16)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Surface(
                    shape = CircleShape,
                    color = Theme.colorScheme.background.surfaceHigh,
                    shadowElevation = 4.dp,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clickable { viewModel.processIntent(LocationIntent.MyLocationClick) },
                        contentAlignment = Alignment.Center
                    ) {
                        if (state.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = Theme.colorScheme.primary.primary,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.MyLocation,
                                contentDescription = "موقعي",
                                tint = Theme.colorScheme.primary.primary
                            )
                        }
                    }
                }

                PrimaryButton(
                    text = "تأكيد الموقع",
                    onClick = { viewModel.processIntent(LocationIntent.ConfirmClick) },
                    isEnabled = state.isConfirmEnabled,
                    isLoading = state.isLoading,
                    contentPadding = PaddingValues(vertical = Theme.spacing._12),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
