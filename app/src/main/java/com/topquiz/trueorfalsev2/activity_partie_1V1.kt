package com.topquiz.trueorfalsev2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.view.View
import android.widget.Toast


class activity_partie_1V1 : AppCompatActivity() {
    private var J1: joueur
    private var J2: joueur
    private var partieActuelle: partie

    //Tableau de toutes les questions possibles dans une partie
    private var listeQuestions = ArrayList<question>()


    //ID des 4 boutons sur lequel on devra ajouter des listeners
    lateinit var  idBtnVraiJ1 : Button
    lateinit var idBtnFauxJ1 : Button
    lateinit var idBtnVraiJ2 : Button
    lateinit var idBtnFauxJ2 : Button

    init {
        J1 = joueur("J1")
        J2 = joueur("J2")


        listeQuestions.add(question("La capitale de l'Allemagne est Berlin",1))
        listeQuestions.add(question("La capitale de la France est Marseille",0))
        listeQuestions.add(question("La capitale de l'Espagne est Madrid ",1))
        listeQuestions.add(question("La capitale de la Chine est Shangai ",1))
        partieActuelle = partie(J1,J2,listeQuestions)



    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partie1_v1)



        idBtnVraiJ1 = findViewById<Button>(R.id.btnVraiJ1)
        idBtnFauxJ1 = findViewById<Button>(R.id.btnFauxJ1)
        idBtnVraiJ2 = findViewById<Button>(R.id.btnVraiJ2)
        idBtnFauxJ2 = findViewById<Button>(R.id.btnFauxJ2)

         majAffichage()

        val clickListener = View.OnClickListener{ boutonClique ->
            val choixReponse : Int
            when (boutonClique.id) {
                // Si le bouton qui a été cliqué correspond au bouton qui a cet id (btnVraiJ1 ...)
                // Alors cela veut dire que le J1 ou le J2 (en fonction du côté de l'écran) a
                // choisi la réponse VRAI (choixReponse = 1) OU Fausse (choixReponse = 0)
                idBtnVraiJ1.id -> {
                    choixReponse = 1
                    afficherToast(choixReponse)
                    partieActuelle.resultatReponse(choixReponse,J1)

                }
                idBtnFauxJ1.id -> {
                    choixReponse = 0
                    afficherToast(choixReponse)
                    partieActuelle.resultatReponse(choixReponse,J1)

                }
                idBtnVraiJ2.id -> {
                    choixReponse = 1
                    afficherToast(choixReponse)
                    partieActuelle.resultatReponse(choixReponse,J2)
                }
                idBtnFauxJ2.id -> {
                    choixReponse = 0
                    afficherToast(choixReponse)
                    partieActuelle.resultatReponse(choixReponse,J2)
                }
            }
            // On change la question car quelqu'un y a répondu + on affiche la suivante + nouveaux points
            majAffichage()
        }

        // On Assigne chaque bouton au listener crée plus haut
        idBtnVraiJ1.setOnClickListener(clickListener)
        idBtnFauxJ1.setOnClickListener(clickListener)
        idBtnVraiJ2.setOnClickListener(clickListener)
        idBtnFauxJ2.setOnClickListener(clickListener)

    }

    // Méthode qui met à jour les points + question et ouvre la page de résultat
    fun majAffichage() {
        if (partieActuelle.getEtatPartie() == 1) {
            // On met à jour l'affichage
            showQuestion()
            showScore()
        }else {
            // On ouvre la page de résultat
            val intent = Intent(this, activity_resultat::class.java)

            // On passe les pseudo des vainqueurs / perdants en paramètre à la page résultat
            if (partieActuelle.determinaisonVainqueur() == "EGALITE") {
                intent.putExtra("Resultat","EGALITE")
            }
            intent.putExtra("partieActuelleGagnant",partieActuelle.determinaisonVainqueur())
            intent.putExtra("partieActuellePerdant",partieActuelle.getPerdant())
            intent.putExtra("nbPointsJVainqueur",partieActuelle.getVainqueurObjet().getNbPoints())
            intent.putExtra("nbPointsJPerdant",partieActuelle.getPerdantObjet().getNbPoints())
            print(partieActuelle.getPerdantObjet().getNbPoints())
            intent.putExtra("nbPointsJ2",J2.getNbPoints())
            intent.putExtra("Resultat","VICTOIRE ")
            startActivity(intent)
            finish()
        }


    }
    //Méthode qui affiche un toast (positif ou négatif en fonction de la réponse)
    fun afficherToast(reponseChoisie : Int){
        var text : String
        if(reponseChoisie == partieActuelle.getReponseQuestionActuelle()) {
            text = "+1"
        }else {
            text = "-1"
        }

        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(this,text, duration) // in Activity
        toast.show()
    }

    //Méthode qui affiche le score de chaque joueur sur l'écran
    fun showScore() {
        val scorePointsJ1 = findViewById<TextView>(R.id.nbPointsJ1)
        val scorePointsJ2 = findViewById<TextView>(R.id.nbPointsJ2)
        scorePointsJ1 .text = partieActuelle.getScore(J1).toString()
        scorePointsJ2 .text = partieActuelle.getScore(J2).toString()
    }



    // Méthode qui affiche la question actuelle pour chaque joueur à l'écran
    fun showQuestion() {
        val idQuestionJ1 = findViewById<TextView>(R.id.questionJ1)
        val idQuestionJ2 = findViewById<TextView>(R.id.questionJ2)
        idQuestionJ1.text = partieActuelle.getQuestionActuelle()
        idQuestionJ2.text = partieActuelle.getQuestionActuelle()
    }



}

