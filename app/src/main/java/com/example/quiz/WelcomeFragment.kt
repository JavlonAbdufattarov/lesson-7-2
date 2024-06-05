package com.example.quiz

import android.app.ActivityOptions
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import android.widget.Button
import androidx.navigation.navOptions

class WelcomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        val startButton = view.findViewById<Button>(R.id.button)
        startButton.setOnClickListener {
            view.findNavController().navigate(
                R.id.action_welcomeFragment_to_quizFragment,
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
        return view


    }
}