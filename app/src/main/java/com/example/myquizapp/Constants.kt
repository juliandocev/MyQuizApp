package com.example.myquizapp

object Constants {

    fun getQuestions():ArrayList<Question>{
        val questionsList =  ArrayList<Question>() // Crate un array

        // Crate a question
        val que1 = Question(
            1,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina",
            "Austria",
            "Ireland",
            "Australia",
            1

        )
        questionsList.add(que1)
        val que2 = Question(
            2,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "New Zealand",
            "Great Britain",
            "Armenia",
            "Fiji",
            4

        )
        questionsList.add(que2)
        val que3 = Question(
            3,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Germany",
            "Belgium",
            "Armenia",
            "Ireland",
            2

        )
        questionsList.add(que3)
        val que4 = Question(
            4,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Brazil",
            "Singapore",
            "Thailand",
            "Equator",
            1

        )
        questionsList.add(que4)
        val que5 = Question(
            5,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Denmark",
            "Norway",
            "Scotland",
            "USA",
            1

        )
        questionsList.add(que5)
        val que6 = Question(
            6,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Argentina",
            "New Zealand",
            "Fiji",
            "Australia",
            3

        )
        questionsList.add(que6)
        val que7 = Question(
            7,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Belgium",
            "Ireland",
            "Denmark",
            "Germany",
            4

        )
        questionsList.add(que7)
        val que8 = Question(
            8,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Argentina",
            "India",
            "Ireland",
            "Australia",
            2

        )
        questionsList.add(que8)
        val que9 = Question(
            9,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Iraq",
            "Kuwait",
            "Sudan",
            "Algeria",
            2

        )
        questionsList.add(que9)
        val que10 = Question(
            10,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "Fiji",
            "India",
            "New Zealand",
            "Australia",
            3

        )
        questionsList.add(que10)
        return  questionsList
    }
}