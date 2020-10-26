package ch.ost.rj.mge.u01.scorecounter.fragments.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ch.ost.rj.mge.u01.scorecounter.fragments.data.PlayerDatabase
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.PlayerData
import ch.ost.rj.mge.u01.scorecounter.fragments.data.repository.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application): AndroidViewModel(application) {

    private val playerDao = PlayerDatabase.getDatabase(application).playerDao()
    private val repository: PlayerRepository

    val getAllData: LiveData<List<PlayerData>>

    init {
        repository = PlayerRepository(playerDao)
        getAllData = repository.getAllData
    }

    fun insertData(playerData: PlayerData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(playerData)
        }
    }

    fun updateData(playerData: PlayerData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(playerData)
        }
    }

    fun incScore(playerData: PlayerData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.incScore(playerData)
        }
    }

    fun decScore(playerData: PlayerData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.decScore(playerData)
        }
    }

    fun deleteItem(playerData: PlayerData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(playerData)
        }
    }
}