
package com.example.myapplication_salsasalfania_102042300016

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var contentContainer: FrameLayout
    private lateinit var bottomNavigation: BottomNavigationView

    private var username: String = "Relawan"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mengambil nama pengguna dari halaman login
        username = intent.getStringExtra("USERNAME") ?: "Relawan"

        // Menghubungkan komponen navigasi
        contentContainer = findViewById(R.id.contentContainer)
        bottomNavigation = findViewById(R.id.bottomNavigation)

        // Menampilkan halaman Home pertama kali
        tampilkanLayout(R.layout.layout_home)

        // Navigasi ke tiga halaman
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    tampilkanLayout(R.layout.layout_home)
                    true
                }

                R.id.nav_kegiatan -> {
                    tampilkanLayout(R.layout.layout_kegiatan)
                    true
                }

                R.id.nav_profil -> {
                    tampilkanLayout(R.layout.layout_profil)
                    true
                }

                else -> false
            }
        }
    }

    // Menampilkan layout sesuai menu yang dipilih
    private fun tampilkanLayout(layoutId: Int) {
        contentContainer.removeAllViews()

        val halaman = LayoutInflater.from(this)
            .inflate(layoutId, contentContainer, false)

        contentContainer.addView(halaman)

        when (layoutId) {
            R.layout.layout_home -> {
                halaman.findViewById<TextView>(R.id.tvWelcome)?.text =
                    "Halo, $username! 👋"
            }

            R.layout.layout_kegiatan -> {
                aturTombolKegiatan(halaman)
            }

            R.layout.layout_profil -> {
                halaman.findViewById<TextView>(R.id.tvNamaProfil)?.text =
                    username

                halaman.findViewById<Button>(R.id.btnLogout)
                    ?.setOnClickListener {
                        logout()
                    }
            }
        }
    }

    // Mengatur tombol pendaftaran kegiatan relawan
    private fun aturTombolKegiatan(halaman: View) {
        val daftarKegiatan = listOf(
            R.id.btnDaftarPantai to "Bersih-Bersih Pantai",
            R.id.btnDaftarMengajar to "Relawan Mengajar",
            R.id.btnDaftarMakanan to "Berbagi Makanan"
        )

        daftarKegiatan.forEach { (idTombol, namaKegiatan) ->
            halaman.findViewById<Button>(idTombol)
                ?.setOnClickListener {
                    Toast.makeText(
                        this,
                        "Kamu memilih: $namaKegiatan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    // Keluar dari akun dan kembali ke halaman login
    private fun logout() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        startActivity(intent)
        finish()
    }
}
