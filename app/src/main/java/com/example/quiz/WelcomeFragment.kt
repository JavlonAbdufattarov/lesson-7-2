package com.example.quiz

import android.app.ActivityOptions
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import android.widget.Button
import androidx.core.app.ActivityCompat.recreate
import androidx.navigation.navOptions
import java.util.Locale

class WelcomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        val btnChange = view.findViewById<Button>(R.id.btnChange)
        val startButton = view.findViewById<Button>(R.id.button)
        startButton.setOnClickListener {
            view.findNavController().navigate(
                R.id.action_welcomeFragment_to_registration,
                null,
                navOptions {
                    anim {
                        enter = R.anim.anim1
                        exit = R.anim.anim2
                        popEnter = R.anim.anim3
                        popExit = R.anim.anim4
                    }
                }
            ) }
        btnChange.setOnClickListener {
            // Toggle language between English and Russian
            val currentLocale = resources.configuration.locale
            val newLanguage = if (currentLocale.language == "en") "ru" else "en"
            setLocale(newLanguage)
        }
        return view


    }
    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)

        // Restart activity to apply new language
        requireActivity().recreate()
    }
}