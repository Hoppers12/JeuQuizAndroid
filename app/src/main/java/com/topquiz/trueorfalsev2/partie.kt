package com.topquiz.trueorfalsev2

import android.widget.TextView
import android.widget.Toast


// Pour la réponse 0 = Faux ; 1 = Vrai + on passe la liste des questions
class partie (J1:joueur,J2:joueur,questions:ArrayList<question>){

    private var joueur1 : joueur
    private var joueur2 : joueur
    private var nbBonneReponseJ1 : Int = 0
    private var nbBonneReponseJ2 : Int = 0
    private var indiceQuestionActuelle : Int= 0
    private var Listequestions = questions
    private var questionActuelle = questions[indiceQuestionActuelle]
    // 1 si la partie est en cours, 0 si finie
    private var etatPartie : Int = 1
    private var JVainqueur : joueur
    private var JPerdant : joueur


    // Joueur qui vient de cliquer sur un bouton de réponse
    private var joueurQuiARepondu : joueur

    init {
        joueur1 = J1
        joueur2 = J2
        joueurQuiARepondu = joueur1
        JVainqueur = joueur1
        JPerdant = joueur2
    }


    //Méthode qui fourni le score pour le joueur demandé
    fun getScore(J : joueur): Int{
        if (J.pseudo == "J1") {
            return joueur1.getNbPoints()
        }else {
            return joueur2.getNbPoints()
        }
    }


    fun getVainqueur(): String {
        return JVainqueur.pseudo
    }

    fun getVainqueurObjet(): joueur {
        return JVainqueur
    }

    fun getPerdantObjet(): joueur {
        return JPerdant
    }
    fun getPerdant(): String? {
        return JPerdant.pseudo
    }


    fun getQuestionActuelle() : String {
        return questionActuelle.getQuestion()
    }

    fun getReponseQuestionActuelle() : Int {
        return questionActuelle.getReponse()
    }

    // Cette méthode met à jour la question (partie) qui est actuellemment affichée
    // dans le but de la manipuler plus facilement

    fun majPartieActuelle() {
        // On passe à la question suivante
        if (indiceQuestionActuelle < Listequestions.size - 1) {
            indiceQuestionActuelle++
            questionActuelle = Listequestions[indiceQuestionActuelle]
        }else {
            // On indique que la partie est finie car aucune question restante
            etatPartie = 0
            determinaisonVainqueur()
        }
    }

    // Méthode qui enregistre et retourne le gagnant
    fun determinaisonVainqueur(): String {

        if (joueur1.getNbPoints() > joueur2.getNbPoints()) {
            JVainqueur = joueur1
            JPerdant = joueur2
            return joueur1.pseudo
        }else{
            if (joueur1.getNbPoints() < joueur2.getNbPoints()){
                JVainqueur = joueur2
                JPerdant = joueur1
                return joueur2.pseudo
            }else {
                return "EGALITE"
            }
        }
    }

    // Retourne si la partie est encore en cours (=1 ou terminée = 0)
    fun getEtatPartie() : Int{
        return etatPartie
    }

    //Fonction qui choisie si il faut ajouter un point au joueur qui a répondu
    //ou lui en retirer en fonction de sa réponse

    fun resultatReponse(reponseChoisie : Int, joueurQuiAClique : joueur) {
        joueurQuiARepondu = joueurQuiAClique
        if (reponseChoisie == questionActuelle.getReponse()){
            ajouterPoint()
        }else {
            retirerPoint()
        }
        majPartieActuelle()
    }

    fun ajouterPoint() {
        joueurQuiARepondu.ajouterPoint()

    }


    fun retirerPoint() {
        joueurQuiARepondu.retirerPoint()
    }
}