package ch.ost.rj.mge.u01.scorecounter.fragments.list.adapter

import androidx.recyclerview.widget.DiffUtil
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.PlayerData

class PlayerDiffUtil (
    private val oldList: List<PlayerData>,
    private val newList: List<PlayerData>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].name == newList[newItemPosition].name
                && oldList[oldItemPosition].color == newList[newItemPosition].color
                && oldList[oldItemPosition].score == newList[newItemPosition].score
    }

}