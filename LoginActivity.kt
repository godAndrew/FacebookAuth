package com.example.user.facebookauth2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*
import com.facebook.FacebookException
import java.util.*


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var callbackManager : CallbackManager


    override fun onClick(v: View?) {
        when (v){
            login_face ->{
                loginWithFb()
            }
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FacebookSdk.sdkInitialize(applicationContext)
        setContentView(R.layout.activity_login)

        initializeControls()
        //loginWithFb()
    }

//LoginManager.getInstance() //face_login_button
    private fun loginWithFb(){
        //LoginManager.getInstance().logInWithPublishPermissions(this,Arrays.asList("publish_actions"))
        LoginManager.getInstance().logInWithReadPermissions(this,
                Arrays.asList("email","public_profile"/*"user_events"*/))
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

            override fun onSuccess(result: LoginResult) {
                //textStatus.text = "Login Succesfull.\n" + result.accessToken
                goMainScream()

            }

            override fun onCancel() {
                textStatus.text = "Login Cancelled."
                Toast.makeText(applicationContext,"Login Cancelled", Toast.LENGTH_SHORT).show()

            }

            override fun onError(error: FacebookException) {
                textStatus.text = "Login Error: "+error.message
                Toast.makeText(applicationContext,"Login Error: "+error.message, Toast.LENGTH_SHORT).show()

            }

        })
    }

    private fun goMainScream() {
        val intent : Intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK)

        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)


    }


    private fun initializeControls(){
        //face_login_button.setPublishPermissions(Arrays.asList("email","public_profile", "user_friends"))
        login_face.setOnClickListener(this)
        callbackManager = CallbackManager.Factory.create()
    }






}

//
//
//
//
