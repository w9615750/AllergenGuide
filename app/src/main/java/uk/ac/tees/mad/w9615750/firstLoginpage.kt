package uk.ac.tees.mad.w9615750

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class firstLoginpage : AppCompatActivity() {

    private lateinit var fBAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_loginpage)

        findViewById<TextView>(R.id.forgotPasswordTextView).setOnClickListener{
            startActivity(Intent(this,secondRegistationPage::class.java))
        }


        fBAuth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            val email = findViewById<EditText>(R.id.emailEditText).text.toString()
            val password = findViewById<EditText>(R.id.passwordEditText).text.toString()
            loginUser(email, password)
        }


    }

    private fun loginUser(email: String, password: String) {
        fBAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this,MainPage::class.java))
                } else {
                    Toast.makeText(applicationContext,"Login Failed",Toast.LENGTH_SHORT).show()
                }
            }
    }
}