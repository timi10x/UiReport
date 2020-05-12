package com.example.uireport.UI

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uireport.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    lateinit var mAuth : FirebaseAuth


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //initialise the firebase auth
        mAuth = FirebaseAuth.getInstance()

        create_account.setOnClickListener { startActivity(intentFor<SignUp>()) }

        login.setOnClickListener { startActivity(intentFor<Login>()) }

        about_tv.setOnClickListener { startActivity(intentFor<about>()) }
    }

//    override fun onStart() {
//        super.onStart()
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        if(currentUser == null){
////            val intent = Intent(applicationContext , Login::class.java)
////            startActivity(intent)
////            finish()
//            startActivity(intentFor<Login>())
//        }else{
//            toast("Login successful")
//        }
//    }

}
