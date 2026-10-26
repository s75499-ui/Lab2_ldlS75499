package com.example.lab2_ldls75499

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtName = findViewById<EditText>(R.id.editName)
        val edtAge = findViewById<EditText>(R.id.editAge)
        val btnProfile = findViewById<Button>(R.id.btnNextScreen)
        val btnWeb = findViewById<Button>(R.id.btnOpenWeb)
        val btnTxtSharing = findViewById<Button>(R.id.btnShare)

        btnProfile.setOnClickListener {
            val name = edtName.text.toString().trim()
            val age = edtAge.text.toString().trim()

            if (name.isNotEmpty() && age.isNotEmpty()) {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("NAME", name)
                intent.putExtra("AGE", age)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    R.string.error_empty,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        btnWeb.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            startActivity(webIntent)
        }

        btnTxtSharing.setOnClickListener {
            val textShare = "Name: ${edtName.text}, Age: ${edtAge.text}"
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, textShare)
            }
            startActivity(Intent.createChooser(shareIntent, "Share using..."))
        }
    }
}