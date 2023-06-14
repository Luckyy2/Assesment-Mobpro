package org.d3if3001.movies.ui.barang

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.d3if3001.movies.R
import org.d3if3001.movies.databinding.ListBarangBinding

class BarangAdapter(private val data: List<Databarang>) :
            RecyclerView.Adapter<BarangAdapter.ViewHolder>(){
    class ViewHolder(
        private val binding: ListBarangBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(barang: Databarang) = with(binding) {
            binding.tvBarang.text= barang.nama
            binding.tvInggris.text = barang.namaInggris
            binding.imgBarang.setImageResource(barang.imageResId)

            root.setOnClickListener {
                val message = root.context.getString(R.string.message, barang.nama)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListBarangBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

}