package ch.ost.rj.mge.u01.scorecounter.fragments.data

import androidx.room.TypeConverter
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.Color

class Converter {

    @TypeConverter
    fun fromColor(color: Color): String {
        return color.name
    }

    @TypeConverter
    fun toColor(color: String): Color {
        return Color.valueOf(color)
    }
}