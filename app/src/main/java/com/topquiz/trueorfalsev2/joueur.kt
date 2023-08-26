package com.topquiz.trueorfalsev2

class joueur (val pseudo: String) {
    private var nbPoints = 0


    fun ajouterPoint() {
        nbPoints ++
    }

    fun retirerPoint() {
        nbPoints --
    }

    fun getNbPoints(): Int{
        return nbPoints
    }



}