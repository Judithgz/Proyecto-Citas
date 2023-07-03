package com.seedssa.citas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.seedssa.citas.util.PreferenceHelper
import com.seedssa.citas.util.PreferenceHelper.set

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            clearSessionPreference()
            goToLogin()
        }

        val btnReservarCita = findViewById<Button>(R.id.btn_reservar_cita)

        btnReservarCita.setOnClickListener {
            goToCreateAppointment()
        }

    }

    private fun goToCreateAppointment() {
        val i = Intent(this, CreateAppointment::class.java)
        startActivity(i)
    }


    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }


    private fun clearSessionPreference(){
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = false
    }

}