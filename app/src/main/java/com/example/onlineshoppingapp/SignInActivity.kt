package com.example.onlineshoppingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*



class SignInActivity : AppCompatActivity() {

            private lateinit var auth: FirebaseAuth

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_sign_in)
                auth = FirebaseAuth.getInstance()

                // this code here allow the
                btn_login.setOnClickListener {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

            }

            private fun doLogin() {
                if (txt_email.toString().isEmpty()) {
                    txt_email.error = "Please enter email"
                    txt_email.requestFocus()
                    return
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(txt_email.toString()).matches()) {
                    txt_email.error = "Please enter valid email"
                    txt_email.requestFocus()
                    return
                }

                if (txt_password.toString().isEmpty()) {
                    txt_password.error = "Please enter password"
                    txt_password.requestFocus()
                    return
                }

                auth.signInWithEmailAndPassword(txt_email.toString(), txt_password.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            updateUI(user)
                        } else {

                            updateUI(null)
                        }
                    }
            }

            public override fun onStart() {
                super.onStart()
                val currentUser = auth.currentUser
                updateUI(currentUser)
            }

            // tjis funtion here allow the user to vification proces in firbase athuntical
            private fun updateUI(currentUser: FirebaseUser?) {

                if (currentUser != null) {
                    if (currentUser.isEmailVerified) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            baseContext, "Please verify your email address.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        baseContext, "Login failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }




