package org.d3if3001.movies.ui.barang

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if3001.movies.R
import org.d3if3001.movies.databinding.FragmentBarangBinding


class FragmentBarang: AppCompatActivity() {

    private lateinit var binding: FragmentBarangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = BarangAdapter(getData())
            setHasFixedSize(true)
        }

    }
    private fun getData(): List<Databarang> {
        return listOf(
            Databarang("Minyak", "Oil", R.drawable.minyak),
            Databarang("Gula", "Sugar",R.drawable.gula),
            Databarang("Beras", "Rice",R.drawable.beras),
            Databarang("Sambal", "Sauce",R.drawable.sambal),
            Databarang("Kecap", "Ketchup",R.drawable.kecap),
        )
    }
}