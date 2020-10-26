package ch.ost.rj.mge.u01.scorecounter.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ch.ost.rj.mge.u01.scorecounter.R
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.Color
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.PlayerData
import kotlinx.android.synthetic.main.row_layout.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<PlayerData>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.name_text.text = dataList[position].name
        holder.itemView.score.text = dataList[position].score.toString()
        holder.itemView.name_text.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }

        val color = dataList[position].color


        holder.itemView.minus_button.setOnClickListener {
            decScore(position)
            holder.itemView.score.text = dataList[position].score.toString()
        }

        holder.itemView.add_button.setOnClickListener {
            incScore(position)
            holder.itemView.score.text = dataList[position].score.toString()
        }

        when (color) {
            Color.RED -> holder.itemView.color_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.red
                )
            )
            Color.PINK -> holder.itemView.color_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.pink
                )
            )
            Color.PURPLE -> holder.itemView.color_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.purple
                )
            )
            Color.BLUE -> holder.itemView.color_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.blue
                )
            )
            Color.LIGHTBLUE -> holder.itemView.color_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.lightBlue
                )
            )
            Color.TEAL -> holder.itemView.color_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.teal
                )
            )
            Color.GREEN -> holder.itemView.color_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.green
                )
            )
            Color.LIME -> holder.itemView.color_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.lime
                )
            )
            Color.YELLOW -> holder.itemView.color_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.yellow
                )
            )
            Color.ORANGE -> holder.itemView.color_indicator.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.orange
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(playerData: List<PlayerData>) {
        this.dataList = playerData
        notifyDataSetChanged()
    }

    fun incScore(position: Int) {
        dataList[position].score += 1

    }

    fun decScore(position: Int) {
        dataList[position].score -= 1

    }

}