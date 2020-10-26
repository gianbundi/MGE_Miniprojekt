package ch.ost.rj.mge.u01.scorecounter.fragments.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.PlayerData

@Database(entities = [PlayerData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class PlayerDatabase: RoomDatabase() {

    abstract fun playerDao(): PlayerDao

    companion object {

        @Volatile
        private var INSTANCE: PlayerDatabase? = null

        fun getDatabase(context: Context): PlayerDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlayerDatabase::class.java,
                    "player_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}