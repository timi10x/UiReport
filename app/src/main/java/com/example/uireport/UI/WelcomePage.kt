package com.example.uireport.UI

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.uireport.R
import com.example.uireport.adapters.ViewPagerAdapter
import com.example.uireport.fragments.missingItemsFragment
import com.example.uireport.fragments.reportCrimeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_welcome_page.*
import org.jetbrains.anko.email
import org.jetbrains.anko.toast

class WelcomePage : AppCompatActivity() {

    lateinit var mAuth  : FirebaseAuth
    private lateinit var  mDatabaseReference: DatabaseReference
    private lateinit var  mDatabase: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(reportCrimeFragment(), "Report Crime")
        adapter.addFragment(missingItemsFragment(), "Missing Items")
        pager.adapter = adapter
        tab_layout.setupWithViewPager(pager)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayUseLogoEnabled(true)

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()
        //welcome_text.text = mAuth.currentUser!!.displayName

    }

//    override fun onStart() {
//        super.onStart()
//        val mUser = mAuth.currentUser
//
//
//        val mUserReference = mDatabaseReference.child(mUser!!.uid)
//        mUserReference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                welcome_text.text = snapshot.child("username").value as? String
//            }
//            override fun onCancelled(databaseError: DatabaseError) {}
//        })
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sign_out ->{
                signOut()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOut() {
        mAuth.signOut()
    }
}
