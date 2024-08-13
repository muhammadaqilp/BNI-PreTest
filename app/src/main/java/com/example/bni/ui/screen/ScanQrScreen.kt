package com.example.bni.ui.screen

import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.bni.utils.BarcodeAnalyzer
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanQrScreen(qrString: (String) -> Unit) {
    val cameraPermissionState =
        rememberPermissionState(permission = android.Manifest.permission.CAMERA)
    if (cameraPermissionState.status.isGranted) {
        CameraScreen(
            modifier = Modifier.fillMaxSize(),
            qrString = {
                qrString(it)
            })
    } else if (cameraPermissionState.status.shouldShowRationale) {
        Text(text = "Camera Permission Denied")
    } else {
        SideEffect {
            cameraPermissionState.run { launchPermissionRequest() }
        }
        Text(text = "No Camera Permission")
    }
}

@Composable
fun CameraScreen(modifier: Modifier = Modifier, qrString: (String) -> Unit) {
    val localContext = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProvideFuture = remember {
        ProcessCameraProvider.getInstance(localContext)
    }

    AndroidView(modifier = modifier,
        factory = { context ->
            val previewView = PreviewView(context)
            val preview = androidx.camera.core.Preview.Builder().build()
            val selector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()

            preview.setSurfaceProvider(previewView.surfaceProvider)

            val imageAnalysis = ImageAnalysis.Builder().build()
            imageAnalysis.setAnalyzer(
                ContextCompat.getMainExecutor(context),
                BarcodeAnalyzer(rawValue = {
                    qrString(it)
                })
            )

            runCatching {
                cameraProvideFuture.get().bindToLifecycle(
                    lifecycleOwner, selector, preview, imageAnalysis
                )
            }.onFailure {
                Log.e("Camera", "Camera Bind Error: ${it.message}", it)
            }
            previewView
        })
}

@Preview
@Composable
private fun CameraScreenPreview() {
    CameraScreen(qrString = {})
}