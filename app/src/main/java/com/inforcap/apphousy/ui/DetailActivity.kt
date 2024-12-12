package com.inforcap.apphousy.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.inforcap.apphousy.R
import com.inforcap.apphousy.databinding.ActivityDetailBinding
import com.inforcap.apphousy.model.Mansion
import kotlinx.coroutines.flow.collectLatest
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: MansionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras?.getBundle("BUNDLE")
        val mansion = bundle?.getParcelable<Mansion>("MANSION")

        if (mansion != null) {
            Log.i("INFORMATION", mansion.name)
        }

        binding.run {
            mansion?.run {
                collapsinTitle.title = mansion.name
                textDatos.text = DecimalFormat("$ #,###").format(mansion.price) +
                        "\n" + mansion.size + " mt2" + "\n"
                textInformacion.text = mansion.description

                Glide.with(applicationContext)
                    .load(photo)
                    .centerCrop()
                    .error(R.drawable.baseline_error_outline_24)
                    .into(imageMansion)
            }
        }

        binding.fabEmail.setOnClickListener {
            enviarEmail(mansion)
        }

    }

    private fun enviarEmail(mansion: Mansion?){
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.setData(Uri.parse("mailto:"))
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("info@housy.cl"))
        if (mansion != null) {
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Consulta ${mansion.name} | id ${mansion.id}")
            val texto = "Hola,\nVi la propiedad ${mansion.name} y me gustaría que me contactaran a este correo o al siguiente número _________.\nQuedo atento."
            emailIntent.putExtra(Intent.EXTRA_TEXT,texto)
            emailIntent.setType("message/rfc822");
            startActivity(Intent.createChooser(emailIntent, "Email "));
        }

    }

}