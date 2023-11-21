package uk.ac.tees.mad.w9615750

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class secondRegistationPage : AppCompatActivity() {

    private lateinit var FBAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_registation_page)


        FBAuth = FirebaseAuth.getInstance()

        var signupButton :Button = findViewById(R.id.signupButton)

        var emailEditText :EditText = findViewById(R.id.emailEditText)
        var passwordEditText :EditText = findViewById(R.id.passwordEditText)
        var nameEditText :EditText = findViewById(R.id.fullNameEditText)

        signupButton.setOnClickListener {
            val email =emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val fullName = nameEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty() && fullName.isNotEmpty()) {
                FBAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success
                        Toast.makeText(this,"Signup successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,firstLoginpage::class.java))

                    } else {

                        Toast.makeText(this, "Signup failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }


        }
    }
}