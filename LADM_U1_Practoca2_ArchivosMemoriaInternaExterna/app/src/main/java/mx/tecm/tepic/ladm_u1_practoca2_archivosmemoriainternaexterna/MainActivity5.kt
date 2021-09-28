package mx.tecm.tepic.ladm_u1_practoca2_archivosmemoriainternaexterna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main2.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter

class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        //Boton Guardar
        findViewById<Button>(R.id.botonguardar).setOnClickListener {
            val spinnerOperacion = findViewById<Spinner>(R.id.spiner)
            val operacion = spinnerOperacion.selectedItemPosition

            when(operacion) {
                0 -> {
                    //Guardar de forma interna
                    if(guardarEnArchivoInterno()){
                        findViewById<EditText>(R.id.nombre).setText("")
                        findViewById<EditText>(R.id.contenido).setText("")

                        Toast.makeText(this, "SE GUARDO CON EXITO", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(this, "ERROR NO SE PUDO GUARDAR", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                1 -> {
                    //Guardar de forma externa
                    if(guardarEnMemoriaExterna()){
                        findViewById<EditText>(R.id.nombre).setText("")
                        findViewById<EditText>(R.id.contenido).setText("")

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
    }

    private fun guardarEnArchivoInterno() : Boolean {
        try {
            val nombre = findViewById<EditText>(R.id.nombre).text.toString()+".txt"
            val archivo = OutputStreamWriter(openFileOutput(nombre, MODE_PRIVATE))
            var dataContenido = findViewById<EditText>(R.id.contenido).text.toString()

            archivo.write(dataContenido)
            archivo.flush()
            archivo.close()
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

    private fun guardarEnMemoriaExterna() : Boolean {
        try {
            val nombre = findViewById<EditText>(R.id.nombre).text.toString()+".txt"
            var dataContenido = findViewById<EditText>(R.id.contenido).text.toString()

            val tarjetaSD = getExternalFilesDir(null)
            val file = File(tarjetaSD?.absolutePath, nombre)
            Toast.makeText(this,tarjetaSD?.absolutePath, Toast.LENGTH_LONG)
                .show()
            val archivo = OutputStreamWriter(FileOutputStream(file))

            archivo.write(dataContenido)
            archivo.flush()
            archivo.close()
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