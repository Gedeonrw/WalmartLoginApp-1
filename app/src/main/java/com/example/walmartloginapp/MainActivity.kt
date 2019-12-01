package com.example.walmartloginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.walmart.account.User
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var userList = arrayListOf<User>()
    var extraList: ArrayList<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user1 = User("Jean Claude", "Mugenzi", "jmugenzi@mum.edu", "123")
        val user2 = User("Fabrice", "Habineza", "fhabineza@mum.edu", "123")
        val user3 = User("Emmanuel", "Tumwizere", "etumwizere@mum.edu", "123")
        val user4 = User("Felix", "Nikubwimana", "fnikubwimana@mum.edu", "123")
        val user5 = User("Josue", "Masasu", "jmasasu@mum.edu", "123")
        userList = arrayListOf(user1, user2, user3, user4, user5)
    }

    fun login(view: View) {
        var username = username.text.toString();
        var password = password.text.toString();

        var user: User? = null
        var newList = userList

        val intent = intent
        try {
            extraList = intent.getSerializableExtra("userList") as ArrayList<User>
        } catch (e: Exception) {

        }

        if (extraList != null) {
            newList = extraList as ArrayList<User>
        }
        if (newList.size > userList.size) {
            userList = newList
        }
        for (element in userList) {
            if (element.username.equals(username) && element.password.equals(password)) {
                user = element
            }
        }
        if (user != null) {
            Toast.makeText(this, "User Verified", Toast.LENGTH_SHORT).show();
            val intent = Intent(this@MainActivity, ShoppingCategory::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        } else {
            Toast.makeText(this, "User Verification Error!!!", Toast.LENGTH_LONG).show()
        }
    }

    fun forgotPassword(view: View) {
        val intent = Intent(this, RecoverPassword::class.java)
        intent.putExtra("userList", userList)
        startActivity(intent)
    }

    fun createAccount(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        intent.putExtra("userList", userList)
        startActivity(intent)
    }

}
