package com.seedssa.citas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.seedssa.citas.R
import com.seedssa.citas.io.ApiService
import com.seedssa.citas.util.PreferenceHelper
import com.seedssa.citas.util.PreferenceHelper.set
import com.seedssa.citas.util.PreferenceHelper.get
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MenuActivity : AppCompatActivity() {

    private val apiService by lazy {
        ApiService.create()
    }

    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            performLogout()
        }

        val btnReservarCita = findViewById<Button>(R.id.btn_reservar_cita)

        btnReservarCita.setOnClickListener {
            goToCreateAppointment()
        }

        val btnMisCitas = findViewById<Button>(R.id.btn_mis_citas)
        btnMisCitas.setOnClickListener {
        goToMyAppointments()
        }
    }

    private fun goToMyAppointments(){
        val intent = Intent(this, AppointmentsActivity::class.java)
        startActivity(intent)
    }


    private fun goToCreateAppointment() {
        val i = Intent(this, CreateAppointmentActivity::class.java)
        startActivity(i)
    }


    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }


    private fun performLogout(){
        val jwt = preferences["jwt", ""]
        val call = apiService.postLogout("Bearer $jwt")
        call.enqueue(object: Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                clearSessionPreference()
                goToLogin()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(applicationContext, "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show()
            }

        })
    }


    private fun clearSessionPreference(){
        preferences["jwt"] = ""
    }

}