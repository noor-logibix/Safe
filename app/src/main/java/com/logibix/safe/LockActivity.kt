package com.logibix.safe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.logibix.safe.databinding.ActivityLockBinding

class LockActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLockBinding
    private var pin = "1234"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUnlock.setOnClickListener {
            unlock()
        }

    }

    private fun unlock() {
        if (pin == binding.etPin.text.toString()) {
            getSharedPreferences(SAFE_PREFERENCE, MODE_PRIVATE).edit().putBoolean(IS_LOCKED, false)
                .apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            /*getSharedPreferences(SAFE_PREFERENCE, MODE_PRIVATE).edit().putBoolean(IS_LOCKED, true)
                .apply()*/
            binding.etPin.error = "Wrong pin"
        }
    }
}