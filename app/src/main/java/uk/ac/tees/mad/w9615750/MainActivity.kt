package uk.ac.tees.mad.w9615750

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var fBAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fBAuth = FirebaseAuth.getInstance()

        val logo = findViewById<ImageView>(R.id.logo)
        val fadeIn: Animation = AnimationUtils.loadAnimation(this, R.anim.fade)
        logo.startAnimation(fadeIn)

        Handler(Looper.getMainLooper()).postDelayed({
            if (fBAuth.currentUser != null) {
                // User is signed in, proceed to main activity
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                // No user is signed in, proceed to login activity
                startActivity(Intent(this, firstLoginpage::class.java))
            }
            finish()
        }, 2000)
    }
}