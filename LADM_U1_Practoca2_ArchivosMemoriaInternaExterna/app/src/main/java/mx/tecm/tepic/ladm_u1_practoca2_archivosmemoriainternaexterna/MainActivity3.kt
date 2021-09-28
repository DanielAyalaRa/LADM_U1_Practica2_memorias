package mx.tecm.tepic.ladm_u1_practoca2_archivosmemoriainternaexterna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.Exception

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        findViewById<Button>(R.id.botonAbrirVen).setOnClickListener {
            val spinnerOperacion = findViewById<Spinner>(R.id.spiner2)
            val operacion = spinnerOperacion.selectedItemPosition

            when(operacion) {
                0 -> {
                    //Leer de forma interna
                    leerDesdeArchivoInterno()
                }
                1 -> {
                    //Leer de forma externa
                    leerDesdeArchivoExterno()
                }
            }
        }
        findViewById<Button>(R.id.botonregresar2).setOnClickListener { finish() }
    }

    private fun leerDesdeArchivoInterno(){
        try {
            val nombre = findViewById<EditText>(R.id.txtAbrir).text.toString()+".txt"
            val archivo = BufferedReader(InputStreamReader (openFileInput(nombre)))

            findViewById<TextView>(R.id.txtAbrirContenido).text = archivo.readLine()
            archivo.close()
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
    private fun leerDesdeArchivoExterno(){
        val nombre = findViewById<EditText>(R.id.txtAbrir).text.toString()+".txt"
        val tarjeta = getExternalFilesDir(null)
        val ruta = File(tarjeta?.absolutePath, nombre)

        try {
            val fIn = FileInputStream(ruta)
            val archivo = InputStreamReader(fIn)
            val br = BufferedReader(archivo)
            var linea = br.readLine()
            val todo = StringBuilder()
            while (linea != null) {
                todo.append(linea + "\n")
                linea = br.readLine()
            }
            br.close()
            archivo.close()
            findViewById<TextView>(R.id.txtAbrirContenido).text = todo

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