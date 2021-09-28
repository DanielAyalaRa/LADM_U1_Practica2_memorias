package mx.tecm.tepic.ladm_u1_practoca2_archivosmemoriainternaexterna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.io.IOException
import java.io.OutputStreamWriter
import java.lang.Exception

class MainActivity2 : AppCompatActivity() {
    val nombre = findViewById<EditText>(R.id.nombre)
    val contenidoNotas = findViewById<EditText>(R.id.contenido)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        try {
            findViewById<Button>(R.id.botonguardar).setOnClickListener {
                val spinnerOperacion = findViewById<Spinner>(R.id.spiner)
                val operacion = spinnerOperacion.selectedItemPosition

                when(operacion){
                    0 -> {
                        //Guardar de forma interna
                        if(guardarEnArchivoInterno()){
                            val nombreLimpiado = nombre.setText("")
                            val contenidoLimpiado = contenidoNotas.setText("")
                            Toast.makeText(this, "SE GUARDO CON EXITO", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(this, "ERROR NO SE PUDO GUARDAR", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }

            findViewById<Button>(R.id.botonregresar1).setOnClickListener {
                finish()
            }
        } catch (io: Exception) {
            io.message
            Toast.makeText(this, "ERROR NO SE PUDO GUARDAR", Toast.LENGTH_LONG)
                .show()
        }

    }

    private fun guardarEnArchivoInterno() : Boolean{
        try {
            val nombreA = nombre.getText().toString()
            val notas = contenidoNotas.getText().toString()

            val archivo = OutputStreamWriter(openFileOutput("${nombre}.txt", MODE_PRIVATE))
            var dataContenido = findViewById<EditText>(R.id.contenido).text.toString()

            archivo.write(dataContenido)
            archivo.flush() //obliga al dispositivo a almacenar en memoria flash (interna)
            archivo.close() //cierra el archivo para evitar que se alteren datos
            return true;
        }catch (io: IOException){
            AlertDialog.Builder(this)
                .setTitle("ATENCIÓN, ERROR")
                .setMessage(io.message)
                .setPositiveButton("ACEPTAR"){dialog, i->
                    dialog.dismiss()
                }
                .show()
            return false;
        }
    }
}