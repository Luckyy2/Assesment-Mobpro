package org.d3if3001.movies.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3001.movies.R
import org.d3if3001.movies.databinding.FragmentHitungBinding
import org.d3if3001.movies.db.DiskonDb
import org.d3if3001.movies.model.HasilDiskon

class HitungDiskonFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding

    private val viewModel: DiskonViewModel by lazy {
        val db = DiskonDb.getInstance(requireContext())
        val factory = HitungDiskonViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[DiskonViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnHitung.setOnClickListener { hitungDiskon() }
        binding.btnClear.setOnClickListener { reset() }
        binding.shareButton.setOnClickListener { shareData() }
        viewModel.getHasilDiskon().observe(requireActivity()) { showResult(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_hitungDiskonFragment_to_historyFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(R.id.action_hitungDiskonFragment_to_aboutFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun hitungDiskon() {
        val harga = binding.hargaInp.text.toString()
        if (TextUtils.isEmpty(harga)) {
            Toast.makeText(context, R.string.harga_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val diskon = binding.diskonInp.text.toString()
        if (TextUtils.isEmpty(diskon)) {
            Toast.makeText(context, R.string.diskon_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitungDiskon(
            harga.toDouble(),
            diskon.toDouble(),
        )
    }

    private fun showResult(result: HasilDiskon?) {
        if (result == null) return
        binding.tvTotal.text = getString(R.string.tvTotal_x, result.jumlahdiskon)

        binding.shareButton.visibility = View.VISIBLE
    }

    private fun shareData() {
        val message = getString(R.string.bagikan_template,
            binding.tvTotal.text,
        )

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }


    private fun reset() {
        binding.hargaInp.text?.clear()
        binding.diskonInp.text?.clear()
        binding.tvTotal.setText("")
    }

}


