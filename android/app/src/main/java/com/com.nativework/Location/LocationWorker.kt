package com.com.nativework.Location

import android.Manifest
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.gms.location.LocationServices
import kotlin.math.log

class LocationWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val TAG = "LocationWorker"

        Log.d(TAG, "Started worker")

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(applicationContext)
        val hasPermission = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) == PermissionChecker.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_COARSE_LOCATION) == PermissionChecker.PERMISSION_GRANTED

        if (!hasPermission) {
            printToast("Location Permission Needed")
            return Result.failure()
        }
        Log.d(TAG, "Permission Granted")

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                Log.d(TAG, "Lat: ${location.latitude}, Lon: ${location.longitude}")
                printToast("Lat: ${location.latitude}, Lon: ${location.longitude}")
            } else {
                Log.d(TAG, "Location is null")
                printToast("Location is null")
            }
        }

        Log.d(TAG, "Thread sleep starts")
        Thread.sleep(1000)
        Log.d(TAG, "Thread sleep ends")

        Log.d(TAG, "Ending Worker")
        return Result.success()
    }

    fun printToast(message: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}