package ch.ost.rj.mge.u01.scorecounter.fragments.data

import androidx.lifecycle.LiveData
import androidx.room.*
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.PlayerData

@Dao
interface PlayerDao {

    @Query("SELECT * FROM players_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<PlayerData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(playerData: PlayerData)

    @Update
    suspend fun updateData(playerData: PlayerData)


    @Delete
    suspend fun deleteItem(playerData: PlayerData)
}