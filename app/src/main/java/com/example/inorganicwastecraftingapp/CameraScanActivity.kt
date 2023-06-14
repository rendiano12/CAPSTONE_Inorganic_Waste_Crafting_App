package com.example.inorganicwastecraftingapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.inorganicwastecraftingapp.databinding.ActivityCameraScanBinding
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.custom.CustomObjectDetectorOptions

class CameraScanActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityCameraScanBinding
    private val binding get() = _binding

    private lateinit var objectDetector: ObjectDetector
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    @SuppressLint("UnsafeOptInUsageError")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCameraScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.pvCameraScan.surfaceProvider)
                }

            try {
                val localModel = LocalModel.Builder()
                    .setAssetFilePath("item_detection.tflite")
                    .build()
                val customObjectDetectorOptions = CustomObjectDetectorOptions.Builder(localModel)
                    .setDetectorMode(CustomObjectDetectorOptions.STREAM_MODE)
                    .enableClassification()
                    .setClassificationConfidenceThreshold(0.5F)
                    .setMaxPerObjectLabelCount(3)
                    .build()
                objectDetector = ObjectDetection.getClient(customObjectDetectorOptions)
                val imageAnalysis = ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), { imageProxy ->
                    val mediaImage = imageProxy.image
                    if (mediaImage != null) {
                        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
                        objectDetector
                            .process(image)
                            .addOnSuccessListener {
                                for (output in it) {
                                    if (output.labels.firstOrNull()?.index != null) {
                                binding.btnCameraScanResult.alpha = 1F
                                        var resultItem: String = ""
                                        if (output.labels[0].index == 2) resultItem =
                                            "Botol Kaca"
                                        else if (output.labels[0].index == 0) resultItem =
                                            "Kaleng"
                                        else if (output.labels[0].index == 1) resultItem =
                                            "Cangkir"
                                        else if (output.labels[0].index == 3) resultItem =
                                            "Kantong Plastik"
                                        else if (output.labels[0].index == 4) resultItem =
                                            "Botol Plastik"
                                        else if (output.labels[0].index == 5) resultItem =
                                            "Tutup Botol Plastik"
                                        else if (output.labels[0].index == 6) resultItem =
                                            "Plastik Kemasan"
                                        else if (output.labels[0].index == 8) resultItem =
                                            "Sterofoam"
                                        else if (output.labels[0].index == 9) resultItem =
                                            "Sedotan"
                                        else resultItem = "Sendok"
                                        binding.btnCameraScanResult.text = resultItem
                                        binding.btnCameraScanResult.setOnClickListener {
                                            val intent = Intent(this, ItemListsActivity::class.java)
                                            intent.putExtra("jenis", resultItem)
                                            startActivity(intent)
                                        }
                                    }
                                }
                                imageProxy.close()
                            }.addOnFailureListener{
                                imageProxy.close()
                            }
                    }
                })

                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    imageAnalysis,
                    preview
                )

            } catch (exc: Exception) {
                Toast.makeText(
                    this,
                    "Gagal memunculkan kamera.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, ContextCompat.getMainExecutor(this))

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
                } else {
                    this.finish()
                    Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
                }
            }
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
            }
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
        }
        supportActionBar?.hide()
        binding.ivCameraScanLayout.setImageResource(R.drawable.scan_layout)
    }
}