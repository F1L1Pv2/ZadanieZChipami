package com.example.zadaniezchipami

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var shoppingItems: Map<String, List<String>>
    private var selectedItems: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ustawienie listy produktów
        shoppingItems = mapOf(
            //10 losowych produktów z każdej kategorii
            "Warzywa" to listOf("Marchewki", "Brokuły", "Sałata", "Pomidor", "Cebula", "Papryka", "Kukurydza", "Kalafior", "Ziemniaki", "Kapusta"),
            "Mięsa Delikatesowe" to listOf("Szynka", "Salami", "Indyk", "Kurczak", "Bekon", "Wędzony Boczek", "Wędzona Kiełbasa", "Wędzony Szynk", "Wędzona Parówka", "Wędzona Karkówka"),
            "Pieczywo" to listOf("Bułka", "Bagietka", "Chleb Pełnoziarnisty", "Chleb biały", "Chleb razowy", "Chleb pszenny", "Chleb żytni", "Chleb żytni", "Chleb żytni", "Chleb żytni")
        )

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        // Ustawienie listenera na zmianę trybu
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.addModeRadioButton -> showAddMode()
                R.id.checkOffModeRadioButton -> showCheckOffMode()
            }
        }
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    private fun showAddMode() {
        //@TODO
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    private fun showCheckOffMode() {
        //@TODO
    }
}