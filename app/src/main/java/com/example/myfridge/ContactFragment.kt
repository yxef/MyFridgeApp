package com.example.myfridge

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myfridge.databinding.FragmentContactBinding
import kotlin.random.Random

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    private val action = ContactFragmentDirections.actionContactFragmentToHomeFragment()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        activity?.title = "Contact us!"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sets up mail button event listener
        binding.buttonToMail.setOnClickListener {
            startActivity(createContactSupportMailIntent())
        }

        // Sets up store button event listener
        binding.buttonStoreReview.setOnClickListener {
            startActivity(createRandomStoreReviewIntent())
        }

        // Callback that responds to onBackPressed and overrides it with a NavHost navigate action
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(action)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    // Creates a mail intent direct at the app's Support Center
    private fun createContactSupportMailIntent(): Intent {
        val subject = "Contact Support"
        val attachment = "HEEEEEEEEEEEEEEELP"
        val intentMail = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, arrayOf("contact@federicogalli.ovh"))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, attachment)
        }

        return intentMail
    }

    // Creates an intent with a randomize google store URI picked from a list
    // Only works with Google Store
    private fun createRandomStoreReviewIntent(): Intent {
        val randomUris = listOf(
            "com.mojang.minecraftpe",
            "com.blizzard.diablo.immortal",
            "com.and.games505.TerrariaPaid",
            "com.chucklefish.stardewvalley",
            "com.chess",
            "com.ninjakiwi.bloonstd6",
            "jp.konami.masterduel"
        )

        return Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                "market://details?id=".plus(
                    randomUris[Random(System.currentTimeMillis()).nextInt(0, randomUris.size - 1)]
                )
            )
        )
    }
}