package dev.ahmdaeyz.gadsleaderboard.util.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

fun Context.isConnected(): Flow<Boolean> {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return callbackFlow {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                launch(Dispatchers.Default) {
                    send(true)
                }
            }

            override fun onLost(network: Network) {
                launch(Dispatchers.Default) {
                    send(false)
                }
            }

            override fun onUnavailable() {
                launch(Dispatchers.Default) {
                    send(false)
                }
            }
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }
}
