package mx.tecm.tepic.ladm_u1_practoca2_archivosmemoriainternaexterna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.lang.Exception

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        findViewById<Button>(R.id.botonBorrarVen).setOnClickListener {
            borrarArchivo()
        }

        findViewById<Button>(R.id.botonregresar3).setOnClickListener {
            finish()
        }
    }

    fun borrarArchivo () {
        try {
            val nombre = findViewById<EditText>(R.id.txtBorrar).text.toString()+".txt"
            val archivo = deleteFile(nombre)
            Toast.makeText(this, "SE BORRO CON EXITO", Toast.LENGTH_LONG)
                .show()
        } catch (io : Exception) {
            AlertDialog.Builder(this)
                .setTitle("ERROR")
                .setMessage(io.message)
                .setNegativeButton("ACEPTAR") {dialog,i->
                    dialog.cancel()
                }
                .show()
        }
    }
}