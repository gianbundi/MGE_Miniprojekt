package ch.ost.rj.mge.u01.scorecounter.fragments

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ch.ost.rj.mge.u01.scorecounter.R
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.Color
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.PlayerData

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkIfDatabaseEmpty(playerData: List<PlayerData>) {
        emptyDatabase.value = playerData.isEmpty()
    }

    val listener: AdapterView.OnItemSelectedListener = object :
        AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            when (position) {
                0 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.red
                        )
                    )
                }
                1 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.pink
                        )
                    )
                }
                2 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.purple
                        )
                    )
                }
                3 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.blue
                        )
                    )
                }
                4 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.lightBlue
                        )
                    )
                }
                5 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.teal
                        )
                    )
                }
                6 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.green
                        )
                    )
                }
                7 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.lime
                        )
                    )
                }
                8 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.yellow
                        )
                    )
                }
                9 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(
                        ContextCompat.getColor(
                            application,
                            R.color.orange
                        )
                    )
                }
            }
        }
    }

    fun verifyDataFromUser(name: String): Boolean {
        return if (TextUtils.isEmpty(name)) {
            false
        } else name.isNotEmpty()
    }

    fun parseColor(color: String): Color {
        return when (color) {
            "Red" -> {
                Color.RED
            }
            "Pink" -> {
                Color.PINK
            }
            "Purple" -> {
                Color.PURPLE
            }
            "Blue" -> {
                Color.BLUE
            }
            "Light blue" -> {
                Color.LIGHTBLUE
            }
            "Teal" -> {
                Color.TEAL
            }
            "Green" -> {
                Color.GREEN
            }
            "Lime" -> {
                Color.LIME
            }
            "Yellow" -> {
                Color.YELLOW
            }
            "Orange" -> {
                Color.ORANGE
            }
            else -> Color.RED
        }
    }

    fun parseColorToInt(color: Color): Int {
        return when (color) {
            Color.RED -> 0
            Color.PINK -> 1
            Color.PURPLE -> 2
            Color.BLUE -> 3
            Color.LIGHTBLUE -> 4
            Color.TEAL -> 5
            Color.GREEN -> 6
            Color.LIME -> 7
            Color.YELLOW -> 8
            Color.ORANGE -> 9
        }
    }
}