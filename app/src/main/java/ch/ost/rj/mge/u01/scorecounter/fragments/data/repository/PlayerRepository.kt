package ch.ost.rj.mge.u01.scorecounter.fragments.data.repository

import androidx.lifecycle.LiveData
import ch.ost.rj.mge.u01.scorecounter.fragments.data.PlayerDao
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.PlayerData

class PlayerRepository(private val playerDao: PlayerDao) {

    val getAllData: LiveData<List<PlayerData>> = playerDao.getAllData()

    suspend fun insertData(playerData: PlayerData) {
        playerDao.insertData(playerData)
    }

    suspend fun updateData(playerData: PlayerData) {
        playerDao.updateData(playerData)
    }

    suspend fun deleteItem(playerData: PlayerData) {
        playerDao.deleteItem(playerData)
    }
}