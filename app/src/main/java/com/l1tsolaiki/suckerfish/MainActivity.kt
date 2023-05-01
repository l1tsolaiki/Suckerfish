package com.l1tsolaiki.suckerfish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.l1tsolaiki.suckerfish.databinding.ActivityMainBinding

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            Log.i("onCreate", "In listener 1")
            sendMessage(it)
        }
        binding.button2.setOnClickListener {
            Log.i("onCreate", "In listener 1")
            musicActivity(it)
        }
    }

    fun sendMessage(view: View) {
        Toast.makeText(baseContext, "Message sent!" , Toast.LENGTH_SHORT).show()
        Log.i("sendMessage", "message sent.")
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)

        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, binding.editTextTextPersonName.text.toString())
        }
        startActivity(intent)
    }

    fun musicActivity(view: View) {
        val intent = Intent(this, MusicActivity::class.java)
        startActivity(intent)
    }
}