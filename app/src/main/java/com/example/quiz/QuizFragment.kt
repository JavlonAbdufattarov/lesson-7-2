package com.example.quiz

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.quiz.QuizFragmentDirections

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFragment : Fragment(),View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private lateinit var tvOptionOne: TextView
    private lateinit var tvOptionTwo: TextView
    private lateinit var tvOptionThree: TextView
    private lateinit var tvOptionFour: TextView
    private lateinit var btnSubmit: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var tvProgress: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var btn_back:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        // Inflate the layout for this fragment
        btn_back = view.findViewById(R.id.btn_back)
        btn_back.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_quizFragment_to_welcomeFragment)
            it.onFinishTemporaryDetach()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvOptionOne = view.findViewById(R.id.tv_option_one)
        tvOptionTwo = view.findViewById(R.id.tv_option_two)
        tvOptionThree = view.findViewById(R.id.tv_option_three)
        tvOptionFour = view.findViewById(R.id.tv_option_four)
        btnSubmit = view.findViewById(R.id.btn_submit)
        progressBar = view.findViewById(R.id.progressBar)
        tvProgress = view.findViewById(R.id.tv_progress)
        tvQuestion = view.findViewById(R.id.tv_question)

        mQuestionsList = Constants.getQuestions()  // Assuming Constants is your object that provides questions
        setQuestion()

        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        tvOptionFour.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> selectedOptionView(tvOptionOne, 1)
            R.id.tv_option_two -> selectedOptionView(tvOptionTwo, 2)
            R.id.tv_option_three -> selectedOptionView(tvOptionThree, 3)
            R.id.tv_option_four -> selectedOptionView(tvOptionFour, 4)
            R.id.btn_submit -> handleSubmit()
        }

    }

    @SuppressLint("SuspiciousIndentation")
    private fun handleSubmit() {
        if (mSelectedOptionPosition == 0) {
            mCurrentPosition++
            if (mCurrentPosition <= mQuestionsList!!.size) {
                setQuestion()
            } else {
                val action = QuizFragmentDirections.actionQuizFragmentToResult(mCorrectAnswers.toString(), mQuestionsList!!.size)
                    findNavController().navigate(action)
            }
        } else {
            val question = mQuestionsList?.get(mCurrentPosition - 1)
            if (question!!.correctAnswer != mSelectedOptionPosition) {
                answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
            } else {
                mCorrectAnswers++
            }
            answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

            if (mCurrentPosition == mQuestionsList!!.size) {
                btnSubmit.text = "FINISH"
            } else {
                btnSubmit.text = "GO TO NEXT QUESTION"
            }

            mSelectedOptionPosition = 0
        }
    }

    private fun setQuestion() {
        val question = mQuestionsList!!.get(mCurrentPosition - 1)
        defaultOptionsView()

        btnSubmit.animate().apply {
            // Создать ObjectAnimator для изменения альфа-канала
            val fadeIn = ObjectAnimator.ofFloat(btnSubmit, "alpha", 0.0f, 1.0f)

         // Задать продолжительность анимации
            fadeIn.setDuration(1000)

            // Запустить анимацию
            fadeIn.start()
        }

        if (mCurrentPosition == mQuestionsList!!.size) {
            btnSubmit.text = "FINISH"
        } else {
            btnSubmit.text = "SUBMIT"
        }

        progressBar.progress = mCurrentPosition
        tvProgress.text = "$mCurrentPosition/${progressBar.max}"

        tvQuestion.text = question.question
        tvOptionOne.text = question.optionOne
        tvOptionTwo.text = question.optionTwo
        tvOptionThree.text = question.optionThree
        tvOptionFour.text = question.optionFour
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(requireContext(), R.drawable.selected_option_border_bg)
    }

    private fun defaultOptionsView() {
        val options = listOf(tvOptionOne, tvOptionTwo, tvOptionThree, tvOptionFour)
        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(requireContext(), R.drawable.default_option_border_bg)
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        val option = when (answer) {
            1 -> tvOptionOne
            2 -> tvOptionTwo
            3 -> tvOptionThree
            4 -> tvOptionFour
            else -> null
        }
        option?.background = ContextCompat.getDrawable(requireContext(), drawableView)
    }
}