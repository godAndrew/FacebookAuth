package com.example.user.facebookauth2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.facebook.AccessToken
import com.facebook.Profile
import com.facebook.login.LoginManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var profile: Profile


    override fun onClick(v: View?) {
        when (v) {
            button_logOut -> {
                LoginManager.getInstance().logOut()
                goLoginScream()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //LoginManager.getInstance().logOut()// Caso suas permissoes buguem desconecte o usuario aqui depois comente essa linha
        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScream()
        }


    }

    override fun onStart() {
        super.onStart()
        if (AccessToken.getCurrentAccessToken() != null) {
            profile = Profile.getCurrentProfile()
            button_logOut.setOnClickListener(this)
            text_Infos.text = profile.firstName + " " + profile.lastName
            carregarImagem()


        }
    }


    private fun carregarImagem() {
        profile.getProfilePictureUri(200, 200)

        Picasso.with(img_perfil.context).load(profile.getProfilePictureUri(200, 200)).into(img_perfil)

    }

    private fun goLoginScream() {

        val intent: Intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


}
