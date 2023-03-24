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


        // Ustawienie trybu dodawania
        val vegetablesCheckboxGroup = findViewById<ChipGroup>(R.id.vegetablesCheckboxGroup)
        val deliMeatsCheckboxGroup = findViewById<ChipGroup>(R.id.deliMeatsCheckboxGroup)
        val breadCheckboxGroup = findViewById<ChipGroup>(R.id.breadCheckboxGroup)

        // Wyczyszczenie grup
        vegetablesCheckboxGroup.removeAllViews()
        deliMeatsCheckboxGroup.removeAllViews()
        breadCheckboxGroup.removeAllViews()

        val shoppingItemList = shoppingItems.values.flatten()
        for (item in shoppingItemList) {
            val checkbox = Chip(this)
            checkbox.text = item
            checkbox.isCheckable = true
            checkbox.tag = shoppingItems.entries.firstOrNull { it.value.contains(item) }?.key

            // Ustawienie listenera na zmianę stanu checkboxa
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedItems.add(item)
                } else {
                    selectedItems.remove(item)
                }
            }

            // Ustawienie koloru checkboxa
            when (checkbox.tag) {
                "Warzywa" -> {
                    checkbox.chipBackgroundColor =
                        resources.getColorStateList(R.color.vegetablesColor)
                }
                "Mięsa Delikatesowe" -> {
                    checkbox.chipBackgroundColor =
                        resources.getColorStateList(R.color.deliMeatsColor)
                }
                "Pieczywo" -> {
                    checkbox.chipBackgroundColor = resources.getColorStateList(R.color.breadColor)
                }
            }

            // Dodanie checkboxa do odpowiedniej grupy
            when (checkbox.tag) {
                "Warzywa" -> vegetablesCheckboxGroup.addView(checkbox)
                "Mięsa Delikatesowe" -> deliMeatsCheckboxGroup.addView(checkbox)
                "Pieczywo" -> breadCheckboxGroup.addView(checkbox)
            }
        }


    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    private fun showAddMode() {
        val checkboxesLayout = findViewById<View>(R.id.checkboxesLayout)
        val chipsLayout = findViewById<View>(R.id.chipsLayout)
        // Pokazanie checkboxów do dodawania produktów
        checkboxesLayout.visibility = View.VISIBLE
        chipsLayout.visibility = View.GONE
    }

    @SuppressLint("UseCompatLoadingForColorStateLists")
    private fun showCheckOffMode() {
        val checkboxesLayout = findViewById<View>(R.id.checkboxesLayout)
        val chipsLayout = findViewById<View>(R.id.chipsLayout)
        // Pokazanie chipsów do odznaczania produktów
        checkboxesLayout.visibility = View.GONE
        chipsLayout.visibility = View.VISIBLE

        // Wyczyszczenie grupy chipsów
        val chipsGroup = findViewById<ChipGroup>(R.id.chipsGroup)
        chipsGroup.removeAllViews()

        // Dodanie chipsów do grupy
        for ((groupName, items) in shoppingItems) {
            for (item in items) {
                if (selectedItems.contains(item)) {
                    val chip = Chip(this)
                    chip.text = item
                    chip.isCloseIconVisible = true
                    chip.tag = groupName

                    // Ustawienie koloru chipsa
                    when (groupName) {
                        "Warzywa" -> {
                            chip.chipBackgroundColor =
                                resources.getColorStateList(R.color.vegetablesColor)
                        }
                        "Mięsa Delikatesowe" -> {
                            chip.chipBackgroundColor =
                                resources.getColorStateList(R.color.deliMeatsColor)
                        }
                        "Pieczywo" -> {
                            chip.chipBackgroundColor =
                                resources.getColorStateList(R.color.breadColor)
                        }
                    }
                    // Dodanie chipsa do grupy
                    chipsGroup.addView(chip)

                    // Ustawienie listenera na kliknięcie ikony zamknięcia
                    chip.setOnCloseIconClickListener {
                        selectedItems.remove(item)
                        chipsGroup.removeView(chip)
                    }
                }
            }
        }
    }
}