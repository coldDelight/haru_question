package com.colddelight.domain.model

data class DomainQnA(
    val answer: List<String>,
    val question: String,
    val date: List<String>,
    val quote: String,
    val quoteAuthor: String,
    val id: Int,
    val a_id: List<Int>
)

data class Stu(
    val name: String,
    val sid: Int,
    val grade:Int


    )

