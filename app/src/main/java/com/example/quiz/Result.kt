package com.example.quiz

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController


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

        tvScore.text = "Your Score is $correctAnswers out of $totalQuestions."

        btnAgain.setOnClickListener {
            // Запуск QuizFragment снова
            activity?.let {
                findNavController().navigate(R.id.action_result_to_quizFragment)

            }
        }
        btnFinish.setOnClickListener {
            // Возвращение на главный экран
            findNavController().navigate(R.id.action_result_to_welcomeFragment)

        }
    }
}