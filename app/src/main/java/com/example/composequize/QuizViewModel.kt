package com.example.composequize

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class QuizViewModel:ViewModel() {

    var qlist:MutableList<QuizItem> = mutableListOf(
        QuizItem(
            1,
        "what is the capital city of saudi Arabia ?",
        listOf<String>("Ryaidh","jeddah","makkah"),
            "Ryaidh"),

        QuizItem(
            2,
            "what is color Black ?",
            listOf<String>("Black","pink","green"),
            "Black"),

        QuizItem(
            3,
            "what is your favourite color ?",
            listOf<String>("Black","pink","green"),
            "pink"),

    )

    val score = mutableStateOf("")

    fun checkAnswer(QuizItem:QuizItem,answer:String){
        qlist = qlist.map {
            if (it.id == QuizItem.id && answer == it.corr){
                it.copy(isAnswcorr = true)
            }else if(it.id ==QuizItem.id && answer != it.corr ){
                it.copy(isAnswcorr = false)
            }else{
                it
            }
        }.toMutableList()
    }

    fun onSubmit(){
        val numOfCorrectAns= qlist.filter{ it.isAnswcorr == true }.size
        score.value = "you got ${numOfCorrectAns} out of ${qlist.size}"
    }
}