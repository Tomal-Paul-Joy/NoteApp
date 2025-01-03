package com.example.lastthree.NoteApp

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lastthree.R
import java.util.logging.Handler

class NotelySplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notely_splash)
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            navigateToHomeScreen()
        }, 3000)

    }
    private fun navigateToHomeScreen(){
        val intent = Intent(this, NotelyHomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}