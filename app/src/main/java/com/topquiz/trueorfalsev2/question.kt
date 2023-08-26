package com.topquiz.trueorfalsev2

// resultat = 0 = faux // resultat = 1 = vrai

class question(question:String, reponse: Int) {
    private var question = question
    private var reponse = reponse

    fun getQuestion(): String {
        return question
    }
    fun getReponse() : Int {
        return reponse
    }

}