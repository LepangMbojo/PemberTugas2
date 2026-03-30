package com.example.bacabaca

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.app.Dialog
import android.graphics.Color

class MainActivity : AppCompatActivity() {
    data class Pertanyaan(
        val teks: String,
        val pilihan: List<String>,
        val jawabanBenarIndex: Int
    )

    private val daftarPertanyaan = listOf(
        Pertanyaan(
            "Bahasa pemrograman apa yang resmi didukung Google untuk membuat aplikasi Android saat ini?",
            listOf("Java", "Kotlin", "C++", "Python"),
            1
        ),
        Pertanyaan(
            "Apa singkatan dari HTML?",
            listOf("Hyper Text Markup Language", "High Text Machine Language", "Hyperlink Text Markup Language", "Home Tool Markup Language"),
            0
        ),
        Pertanyaan(
            "Simbol apa yang digunakan untuk komentar satu baris di Java dan Kotlin?",
            listOf("", "/* */", "//", "#"),
            2
        )
    )

    // Variabel untuk melacak progres kuis
    private var indeksPertanyaanSaatIni = 0

    // Variabel untuk menghubungkan UI
    private lateinit var tvPertanyaan: TextView
    private lateinit var btnPilihan1: Button
    private lateinit var btnPilihan2: Button
    private lateinit var btnPilihan3: Button
    private lateinit var btnPilihan4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPertanyaan = findViewById(R.id.tvPertanyaan)
        btnPilihan1 = findViewById(R.id.btnPilihan1)
        btnPilihan2 = findViewById(R.id.btnPilihan2)
        btnPilihan3 = findViewById(R.id.btnPilihan3)
        btnPilihan4 = findViewById(R.id.btnPilihan4)

        tampilkanPertanyaan()

        btnPilihan1.setOnClickListener { cekJawaban(0) }
        btnPilihan2.setOnClickListener { cekJawaban(1) }
        btnPilihan3.setOnClickListener { cekJawaban(2) }
        btnPilihan4.setOnClickListener { cekJawaban(3) }
    }

    private fun tampilkanPertanyaan() {

        val pertanyaan = daftarPertanyaan[indeksPertanyaanSaatIni]

        tvPertanyaan.text = pertanyaan.teks
        btnPilihan1.text = pertanyaan.pilihan[0]
        btnPilihan2.text = pertanyaan.pilihan[1]
        btnPilihan3.text = pertanyaan.pilihan[2]
        btnPilihan4.text = pertanyaan.pilihan[3]
    }

    private fun cekJawaban(indeksTerpilih: Int) {
        val pertanyaan = daftarPertanyaan[indeksPertanyaanSaatIni]

        val isBenar = (indeksTerpilih == pertanyaan.jawabanBenarIndex)


        tampilkanPopup(isBenar)
    }

    private fun tampilkanPopup(isBenar: Boolean) {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_hasil)
        dialog.setCancelable(false)

        val tvJudul = dialog.findViewById<TextView>(R.id.tvJudulDialog)
        val btnLanjut = dialog.findViewById<Button>(R.id.btnLanjutDialog)


        if (isBenar) {
            tvJudul.text = "Luwh Benar"
            tvJudul.setTextColor(Color.parseColor("#4CAF50"))
        } else {
            tvJudul.text = "Luwh Salah"
            tvJudul.setTextColor(Color.parseColor("#F44336"))
        }

        btnLanjut.setOnClickListener {
            dialog.dismiss()
            lanjutKePertanyaanBerikutnya()
        }

        dialog.show()
    }

    private fun lanjutKePertanyaanBerikutnya() {
        indeksPertanyaanSaatIni++

        if (indeksPertanyaanSaatIni >= daftarPertanyaan.size) {
            indeksPertanyaanSaatIni = 0
        }
        tampilkanPertanyaan()
    }
}