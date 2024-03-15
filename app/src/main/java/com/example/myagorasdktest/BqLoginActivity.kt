package com.example.myagorasdktest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bqchatsdk.constants.BQChatConstants
import com.example.bqchatsdk.helper.BQChatAccountManager

class BqLoginActivity : AppCompatActivity() {
    private val chatAccountManager by lazy { BQChatAccountManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bq_login)

        findViewById<Button>(R.id.button_acessar).setOnClickListener {
            signIn(
                findViewById<EditText>(R.id.edt_phone).text.toString(),
                findViewById<EditText>(R.id.edt_password).text.toString()
            )
        }
    }

    private fun signIn(phone: String?, password: String?) {
        if (isUserDataAcceptable(phone, password)) {
            signInWithAgora()
        } else {
            showToast("Data it's wrong, cannot sign in")
        }
    }

    private fun isUserDataAcceptable(phone: String?, password: String?): Boolean {
        return !phone.isNullOrEmpty() && !password.isNullOrEmpty()
    }

    private fun signInWithAgora(
        userId: String = BQChatConstants.USER_ID,
        token: String = BQChatConstants.TOKEN
    ) {
        if (userId.isNotEmpty() && token.isNotEmpty()) {
            chatAccountManager.login(userId, token) { isSuccess: Boolean, errorCode: Int?, errorMsg: String? ->
                if (isSuccess) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    runOnUiThread {
                        showToast("error: $errorCode error message: $errorMsg")
                    }
                }
            }
        }else{
            showToast("Data it's wrong, cannot sign in with Agora")
        }
    }

    private fun showToast(msg:String){
        Toast.makeText(
            this,
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }
}