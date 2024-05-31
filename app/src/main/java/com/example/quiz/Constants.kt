package com.example.quiz

object Constants {
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    fun getQuestions(): ArrayList<Question>{
        val questionList = ArrayList<Question>()
        val que1 = Question(
            1,
            "... the capital of Uzbekistan ",
            "Tashkent",
            "Samarkand",
            "Bukhara",
            "Xiva",
            1
        )
        questionList.add(que1)
        val que2 = Question(
            2,
            "2+2=..",
            "4",
            "5",
            "6",
            "7",
            1
        )
        questionList.add(que2)
        val que3=Question(
            3,
            "I study in ...",
            "Geek Brains",
            "SkillBox",
            "in YouTube",
            "?",
            1
        )
        questionList.add(que3)

        return questionList
    }
}