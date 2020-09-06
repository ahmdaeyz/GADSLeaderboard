package dev.ahmdaeyz.gadsleaderboard.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.ahmdaeyz.gadsleaderboard.R
import dev.ahmdaeyz.gadsleaderboard.ui.main.MainActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        MainScope().launch {
            delay(2000)
            val mainActivityIntent = Intent(
                this@SplashActivity,
                MainActivity::class.java
            )
            startActivity(mainActivityIntent)
            this@SplashActivity.finish()
        }
    }
}
