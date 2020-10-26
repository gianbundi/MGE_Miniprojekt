package ch.ost.rj.mge.u01.scorecounter.fragments.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.Color
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "players_table")
@Parcelize
data class PlayerData (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var color: Color,
    var score: Int = 0
): Parcelable