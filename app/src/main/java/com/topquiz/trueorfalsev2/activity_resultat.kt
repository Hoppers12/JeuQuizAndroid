package com.topquiz.trueorfalsev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewDebug.IntToString
import android.widget.Button
import android.widget.TextView

class activity_resultat() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultat)

        val idTxtVainqueur = findViewById<TextView>(R.id.txtJVainqueur)
        val idTxtPerdant = findViewById<TextView>(R.id.txtJPerdant)
        val idBtnRetourAccueil = findViewById<Button>(R.id.btnRetourAccueil)

        val idTxtNbPointsGagnant= findViewById<TextView>(R.id.txtPointsJGagnants)
        val idTxtNbPointsPerdant = findViewById<TextView>(R.id.txtPointsJPerdants)

        val intent2 = Intent(this, MainActivity::class.java)

       idBtnRetourAccueil.setOnClickListener {
            startActivity(intent2)
        }

        val joueurResultat =  intent.extras
        var pseudoGagnant : String?
        var pseudoPerdant : String?
        val nbPointsJVainqueur : Int?
        val nbPointsJPerdant : Int?

        //On récupère les valeurs passées par la page précédente
        nbPointsJVainqueur = joueurResultat?.getInt("NbPointsJVainqueur")
        nbPointsJPerdant = joueurResultat?.getInt("NbPointsJPerdant")
        pseudoGagnant = joueurResultat?.getString("partieActuelleGagnant")
        pseudoPerdant = joueurResultat?.getString("partieActuellePerdant")

        // On modifie la propriété text avec leurs valeurs
        idTxtNbPointsGagnant.text = nbPointsJVainqueur.toString()
        idTxtNbPointsPerdant.text = nbPointsJPerdant.toString()
        idTxtVainqueur.text = pseudoGagnant
        idTxtPerdant.text = pseudoPerdant
    }
}