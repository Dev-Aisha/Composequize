package com.example.composequize

data class QuizItem(
    val id: Int,
    val ques: String,
    val answ: List<String>,
    val corr: String,
    var isAnswcorr: Boolean?=false,
)