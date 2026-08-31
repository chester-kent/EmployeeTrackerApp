package com.example.employeetrackerapp.ui.locations

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.employeetrackerapp.databinding.ActivityLocationViewQrBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter

class LocationViewQr : AppCompatActivity() {
    private lateinit var binding: ActivityLocationViewQrBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            ActivityLocationViewQrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val qrValue =
            intent.getStringExtra("QR_VALUE") ?: ""

        binding.tvQrValue.text = qrValue
    }

    @SuppressLint("UseKtx")
    private fun generateQrCode(value: String) {
        if (value.isEmpty()) {
            return
        }

        val bitMatrix =
            MultiFormatWriter().encode(
                value,
                BarcodeFormat.QR_CODE,
                500,
                500
            )

        val width = bitMatrix.width
        val height = bitMatrix.height

        val bitmap =
            Bitmap.createBitmap(
                width,
                height,
                Bitmap.Config.RGB_565
            )

        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(
                    x,
                    y,
                    if (bitMatrix[x, y]) {
                        android.graphics.Color.BLACK
                    } else {
                        android.graphics.Color.WHITE
                    }
                )
            }
        }
        binding.imgQrCode.setImageBitmap(bitmap)
    }
}