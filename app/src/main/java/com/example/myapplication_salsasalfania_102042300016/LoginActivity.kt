
package com.example.myapplication_salsasalfania_102042300016

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menampilkan halaman login
        setContentView(R.layout.activity_login)

        // Menghubungkan komponen dari XML
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // Aksi tombol login
        btnLogin.setOnClickListener {

            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString()

            // Validasi nama pengguna
            if (username.isEmpty()) {
                etUsername.error = "Nama pengguna wajib diisi"
                etUsername.requestFocus()
                return@setOnClickListener
            }

            // Validasi kata sandi
            if (password.isEmpty()) {
                etPassword.error = "Kata sandi wajib diisi"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            // Pesan berhasil login
            Toast.makeText(
                this,
                "Selamat datang, $username!",
                Toast.LENGTH_SHORT
            ).show()

            // Membuka halaman utama
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)

            // Menutup halaman login
            finish()
        }
    }
}
