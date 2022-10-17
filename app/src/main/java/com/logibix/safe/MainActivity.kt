package com.logibix.safe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.logibix.safe.databinding.ActivityMainBinding

const val SAFE_PREFERENCE = "SAFE_PREFERENCE"

const val IS_LOCKED = "isLocked"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLock.setOnClickListener {
            setLock(true)
        }

        binding.btnClose.setOnClickListener {
            setLockPreference(true)
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        setLock(getLockPreference())
    }

    override fun onPause() {
        super.onPause()
        setLockPreference(true)
    }

    private fun setLock(isLock: Boolean) {
        setLockPreference(isLock)
        if (isLock) {
            startActivity(Intent(this, LockActivity::class.java))
            finish()
        }
    }

    private fun setLockPreference(isLock: Boolean) {
        getSharedPreferences(SAFE_PREFERENCE, MODE_PRIVATE).edit().putBoolean(IS_LOCKED, isLock)
            .apply()
    }

    private fun getLockPreference() = getSharedPreferences(SAFE_PREFERENCE, MODE_PRIVATE)
        .getBoolean(IS_LOCKED, true)
}
