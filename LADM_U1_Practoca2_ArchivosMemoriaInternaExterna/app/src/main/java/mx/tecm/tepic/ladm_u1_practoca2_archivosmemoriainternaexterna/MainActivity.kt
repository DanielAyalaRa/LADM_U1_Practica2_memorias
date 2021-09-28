package mx.tecm.tepic.ladm_u1_practoca2_archivosmemoriainternaexterna

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_LADM_U1_Practoca2_ArchivosMemoriaInternaExterna)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try{
            findViewById<Button>(R.id.botonmas).setOnClickListener {
                val intento = Intent(this, MainActivity5::class.java)
                startActivity(intento)
            }

            findViewById<ImageButton>(R.id.botonabrir).setOnClickListener {
                val intento2 = Intent(this, MainActivity3::class.java)
                startActivity(intento2)
            }

            findViewById<ImageButton>(R.id.botoneliminar).setOnClickListener {
                val intento3 = Intent(this, MainActivity4::class.java)
                startActivity(intento3)
            }
        } catch(io : Exception){
            io.message
        }
    }
}