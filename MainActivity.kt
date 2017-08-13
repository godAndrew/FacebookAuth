package com.example.user.facebookauth2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.facebook.AccessToken
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v) {
            button_logOut ->{
                LoginManager.getInstance().logOut()
                goLoginScream()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(AccessToken.getCurrentAccessToken() == null){
            goLoginScream()
        }
        button_logOut.setOnClickListener(this)
        textStatus2.text = "Login Succesfull.\n" + AccessToken.getCurrentAccessToken()
    }


    private fun goLoginScream() {
        val intent : Intent = Intent(this,LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }



}
