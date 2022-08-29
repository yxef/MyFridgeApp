package com.example.myfridge

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myfridge.databinding.FragmentContactBinding
import kotlin.random.Random

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    private val randomUris: List<String> = listOf(
        "com.mojang.minecraftpe",
        "com.blizzard.diablo.immortal",
        "com.and.games505.TerrariaPaid",
        "com.chucklefish.stardewvalley",
        "com.chess",
        "com.ninjakiwi.bloonstd6",
        "jp.konami.masterduel"
    )

    private val emailAddress : Array<String> = arrayOf("contact@federicogalli.ovh")
    private val subject: String = "Contact Support"
    private val attachment = "HEEEEEEEEEEEEEEELP"
    private val intentMail = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:") // only email apps should handle this
        putExtra(Intent.EXTRA_EMAIL, arrayOf("contact@federicogalli.ovh"))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, attachment)
    }

    // Intent to open an app page on the Google Play Store specifically
    // It won't be found on other app stores
    private val intentStore = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(
            "market://details?id=".plus(
                randomUris[Random(System.currentTimeMillis()).nextInt(0, randomUris.size - 1)]
            )
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonToMail.setOnClickListener {
            startActivity(intentMail)
        }

        binding.buttonStoreReview.setOnClickListener {
            startActivity(intentStore)
        }
    }
}