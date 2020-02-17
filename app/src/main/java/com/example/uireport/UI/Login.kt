package com.example.uireport.UI

import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import com.example.uireport.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.email
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class Login : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //using kotlin anko to navigate to signup screen if user doesn't have an account yet
        text_signUp.setOnClickListener { startActivity(intentFor<SignUp>()) }
        Containerusername.setStartIconDrawable(R.drawable.person)

        Containerpassword.setStartIconDrawable(R.drawable.lock)

        initialise()

    }

    private fun initialise() {
        //initializing the firbase auth
        mAuth = FirebaseAuth.getInstance()

        login.setOnClickListener { loginUser() }
    }

    private fun loginUser() {
        val username = username_edit_text.text.toString().trim()
        val password = password_edit_text.text.toString().trim()

        if (username.isEmpty()) {
            Containerusername.error = "enter username"
            Containerusername.setErrorIconDrawable(R.drawable.ic_error_black_24dp)
        }
        if (password.isEmpty()) {
            Containerpassword.error = "enter password"
            Containerpassword.setErrorIconDrawable(R.drawable.ic_error_black_24dp)
        } else {
            loginuser(username, password)
        }
    }

    private fun loginuser(username: String, password: String) {
        val dialog = indeterminateProgressDialog("please wait", "Loading")
        dialog.show()
        mAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener {
                dialog.hide()
                if (it.isSuccessful) {
                    updateUI()
                } else {
                    toast("please try again")
                }
            }
    }

    private fun updateUI() {
        startActivity(intentFor<WelcomePage>())

    }

}
