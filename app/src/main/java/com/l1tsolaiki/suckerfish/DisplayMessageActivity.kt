package com.l1tsolaiki.suckerfish

import android.content.ContentResolver
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import com.l1tsolaiki.suckerfish.databinding.ActivityDisplayMessageBinding

class DisplayMessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayMessageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayMessageBinding.inflate(layoutInflater);
        setContentView(binding.root)

        val msg = intent.getStringExtra(EXTRA_MESSAGE)

        Log.i("DisplayMessageActivity", "in DisplayMessageActivity")

        binding.textView.apply {
            text = msg;
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }
}