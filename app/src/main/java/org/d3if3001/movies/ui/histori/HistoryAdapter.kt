package org.d3if3001.movies.ui.histori

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3001.movies.databinding.ListHistoryBinding
import org.d3if3001.movies.db.DiskonEntity
import org.d3if3001.movies.model.hitungDiskon
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter : ListAdapter<DiskonEntity, HistoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<DiskonEntity>() {
                override fun areItemsTheSame(
                    oldData: DiskonEntity, newData: DiskonEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: DiskonEntity, newData: DiskonEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListHistoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: DiskonEntity) = with(binding) {
            val rnd = Random()
            val color = Color.argb(225, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

            val hasilDiskon = item.hitungDiskon()
            kategoriTextView.text = hasilDiskon.jumlahdiskon.toString().substring(0, 1)
            val circleBg = kategoriTextView.background as GradientDrawable
            circleBg.setColor(color)
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            diskonTextView.text = hasilDiskon.jumlahdiskon.toString()
        }
    }
}