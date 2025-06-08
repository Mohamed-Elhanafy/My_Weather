package com.example.myweather.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.os.Looper
import com.example.myweather.domain.exception.MyWeatherException
import com.example.myweather.domain.location.LocationService
import com.example.myweather.domain.model.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.Locale
import android.location.Location as AndroidLocation

class GoogleLocationService(private val context: Context) : LocationService {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private val geocoder by lazy { Geocoder(context, Locale.getDefault()) }

    companion object {
        private const val LOCATION_UPDATE_INTERVAL_MS = 900_000L // 15 minutes
        private const val MAX_GEOCODER_RESULTS = 1
    }

    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(): Flow<Location> = callbackFlow {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, LOCATION_UPDATE_INTERVAL_MS
        ).build()

        fun buildLocation(androidLocation: AndroidLocation, name: String?): Location =
            Location(
                latitude = androidLocation.latitude,
                longitude = androidLocation.longitude,
                name = name
            )

        fun getLocationNameAsync(latitude: Double, longitude: Double, onResult: (String?) -> Unit) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                geocoder.getFromLocation(latitude, longitude, MAX_GEOCODER_RESULTS, object : Geocoder.GeocodeListener {
                    override fun onGeocode(addresses: MutableList<android.location.Address>) {
                        val address = addresses.firstOrNull()
                        val name =address?.adminArea ?: address?.countryName ?: address?.getAddressLine(0)
                        onResult(name)
                    }
                    override fun onError(errorMessage: String?) {
                        onResult(null)
                    }
                })
            } else {
                @Suppress("DEPRECATION")
                try {
                    val addresses = geocoder.getFromLocation(latitude, longitude, MAX_GEOCODER_RESULTS)
                    val address = addresses?.firstOrNull()
                    val name =  address?.adminArea ?: address?.countryName ?: address?.getAddressLine(0)
                    onResult(name)
                } catch (e: Exception) {
                    onResult(null)
                }
            }
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { androidLocation: AndroidLocation? ->
            androidLocation?.let {
                getLocationNameAsync(it.latitude, it.longitude) { name ->
                    trySend(buildLocation(it, name))
                }
            }
        }

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let { androidLocation ->
                    getLocationNameAsync(androidLocation.latitude, androidLocation.longitude) { name ->
                        trySend(buildLocation(androidLocation, name))
                    }
                }
            }
        }

        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (e: SecurityException) {
            close(MyWeatherException.LocationException("Missing location permissions"))
            return@callbackFlow
        } catch (e: Exception) {
            close(MyWeatherException.LocationException("Failed to get location: ${e.message}"))
            return@callbackFlow
        }

        awaitClose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
}
