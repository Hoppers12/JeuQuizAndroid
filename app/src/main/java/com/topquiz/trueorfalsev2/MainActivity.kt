package com.topquiz.trueorfalsev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1V1 = findViewById<Button>(R.id.bouton1V1)
        val intent = Intent(this, activity_partie_1V1::class.java)
        btn1V1.setOnClickListener {
            startActivity(intent)
        }
    }



}