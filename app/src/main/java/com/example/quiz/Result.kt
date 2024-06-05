package com.example.quiz

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions


class Result : Fragment() {
    // TODO: Rename and change types of parameters
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvScore = view.findViewById<TextView>(R.id.tv_score)
        val btnFinish = view.findViewById<Button>(R.id.btn_finish)
        val btnAgain = view.findViewById<Button>(R.id.btn_again)

        val totalQuestions =  ResultArgs.fromBundle(requireArguments()).max
        val correctAnswers = ResultArgs.fromBundle(requireArguments()).answer
        tvScore.animate().apply {
            // Создать ObjectAnimator для изменения альфа-канала
            val fadeIn = ObjectAnimator.ofFloat(tvScore, "alpha", 0.0f, 1.0f)

            // Задать продолжительность анимации
            fadeIn.setDuration(1000)

            // Запустить анимацию
            fadeIn.start()
        }
        tvScore.text = "Your Score is $correctAnswers out of $totalQuestions."


        btnAgain.setOnClickListener {
            // Запуск QuizFragment снова
            view.findNavController().navigate(
                R.id.action_result_to_quizFragment,
                null,
                navOptions {
                    anim {
                        enter = R.anim.anim1
                        exit = R.anim.anim2
                        popEnter = R.anim.anim3
                        popExit = R.anim.anim4
                    }
                }
            )
        }
        btnFinish.setOnClickListener {
            // Возвращение на главный экран
            findNavController().navigate(R.id.action_result_to_welcomeFragment)

        }
    }
}