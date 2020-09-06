package dev.ahmdaeyz.gadsleaderboard.ui.main.leaderboard.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.ahmdaeyz.gadsleaderboard.R
import dev.ahmdaeyz.gadsleaderboard.data.model.Leader
import dev.ahmdaeyz.gadsleaderboard.databinding.ListItemLayoutBinding

class LeadersListAdapter : RecyclerView.Adapter<LeadersListAdapter.ViewHolder>() {

    private var items = listOf<Leader>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }


    class ViewHolder(private val binding: ListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Leader) {
            binding.badgeImage.load(item.badgeUrl) {
                placeholder(R.color.gray)
            }
            binding.leaderNameText.text = item.name
            binding.summaryText.text = "${item.score.description}, ${item.country}"
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ListItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun bindItems(collection: Collection<Leader>) {
        items = collection.toList()
        notifyDataSetChanged()
    }
}