package com.example.uireport.UI

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.uireport.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.password_edit_text
import kotlinx.android.synthetic.main.activity_sign_up.username_edit_text
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class SignUp : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mDatabase: FirebaseDatabase

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        containerUsername.setStartIconDrawable(R.drawable.person)
        Containerphone.setStartIconDrawable(R.drawable.phone)
        Containeremail.setStartIconDrawable(R.drawable.envelope)
        Containeraddress.setStartIconDrawable(R.drawable.marker)
        Containerpasswords.setStartIconDrawable(R.drawable.lock)

        //initialise the firebase auth
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()

        //using kotlin anko to navigate to login if user already has an account
        text_login.setOnClickListener { startActivity(intentFor<Login>()) }

        signUp.setOnClickListener { createNewAccount() }

    }

    private fun createNewAccount() {
        var username = username_edit_text.text.toString().trim()
        var phoneNumber = phone_edit_text.text.toString().trim()
        var email = email_edit_text.text.toString().trim()
        var address = address_edit_text.text.toString().trim()
        var password = password_edit_text.text.toString().trim()

        if (username.isEmpty()){
            containerUsername.error = "enter username"
            containerUsername.setErrorIconDrawable(R.drawable.ic_error_black_24dp)
        }
        if (phoneNumber.isEmpty()){
            Containerphone.error = "enter phone number"
            Containerphone.setErrorIconDrawable(R.drawable.ic_error_black_24dp)
        }
        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Containeremail.error = "enter email"
            Containeremail.setErrorIconDrawable(R.drawable.ic_error_black_24dp)
        }
        if (address.isEmpty()){
            Containeraddress.error = "enter address"
            Containeraddress.setErrorIconDrawable(R.drawable.ic_error_black_24dp)
        }
        if (password.isEmpty()){
            Containerpasswords.error = "enter password"
            Containerpasswords.setErrorIconDrawable(R.drawable.ic_error_black_24dp)
        }
        else{
            registerUser(username, phoneNumber, email, address, password)
        }

    }

    private fun registerUser(
        username: String?,
        phoneNumber: String?,
        email: String,
        address: String?,
        password: String
    ) {
        var dialog = indeterminateProgressDialog("please wait...", "Loading")
        dialog.show()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                dialog.hide()
                if (it.isSuccessful){
                    val userId = mAuth.currentUser!!.uid

                    val currentUserDb = mDatabaseReference.child(userId)
                    currentUserDb.child("email").setValue(email)
                    currentUserDb.child("password").setValue(password)

                    updateUserInfoAndUI()
                }
                else{
                    toast("please try again")
                }
            }
    }

    private fun updateUserInfoAndUI() {
        startActivity(intentFor<WelcomePage>())
    }

}
