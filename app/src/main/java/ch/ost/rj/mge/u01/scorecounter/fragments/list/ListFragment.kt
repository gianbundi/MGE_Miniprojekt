package ch.ost.rj.mge.u01.scorecounter.fragments.list

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.ost.rj.mge.u01.scorecounter.R
import ch.ost.rj.mge.u01.scorecounter.fragments.SharedViewModel
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.PlayerData
import ch.ost.rj.mge.u01.scorecounter.fragments.data.viewmodel.PlayerViewModel
import ch.ost.rj.mge.u01.scorecounter.fragments.list.adapter.ListAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private val args by navArgs<ListFragmentArgs>()

    private val mPlayerViewModel: PlayerViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private val adapter: ListAdapter by lazy { ListAdapter() }

    companion object {
        var darkModeCurrent = false
        var themeModePref = false
        const val filePath = "ch.ost.rj.mge.u01.preferences"
        const val DARK_MODE = "darkmode"
        private var PRIVATE_MODE = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        setupRecycler(view)

        mPlayerViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            mSharedViewModel.checkIfDatabaseEmpty(data)
            adapter.setData(data)
        })
        mSharedViewModel.emptyDatabase.observe(viewLifecycleOwner, Observer {
            showEmptyDatabaseViews(it)
        })

        view.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        view.saveButton.setOnClickListener {
            val playerList = mPlayerViewModel.getAllData
            val listValues = playerList.value
            val length = listValues?.size
            val arrayLength = length?.minus(1)
            for(x in 0 ..arrayLength!!) {
                mPlayerViewModel.updateData(adapter.dataList[x])
            }
            Toast.makeText(requireContext(), "Scores saved!", Toast.LENGTH_SHORT).show()
        }

        setHasOptionsMenu(true)
        getThemeMode()
        return view
    }

    private fun setupRecycler(view: View) {
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        swipeToDelete(recyclerView)
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemToDelete = adapter.dataList[viewHolder.adapterPosition]
                mPlayerViewModel.deleteItem(itemToDelete)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                restoreDeletedData(viewHolder.itemView, itemToDelete, viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun restoreDeletedData(view: View, deletedItem: PlayerData, position: Int) {
        val snackbar = Snackbar.make(
            view, "Deleted '${deletedItem.name}'", Snackbar.LENGTH_LONG
        )
        snackbar.setAction("Undo") {
            mPlayerViewModel.insertData(deletedItem)
            adapter.notifyItemChanged(position)
        }
        snackbar.show()
    }


    private fun showEmptyDatabaseViews(emptyDatabase: Boolean) {
        if (emptyDatabase) {
            view?.no_data_imageView?.visibility = View.VISIBLE
            view?.no_data_textView?.visibility = View.VISIBLE
        } else {
            view?.no_data_imageView?.visibility = View.INVISIBLE
            view?.no_data_textView?.visibility = View.INVISIBLE
        }

    }


    private fun getThemeMode() {
        val preference: SharedPreferences = layoutInflater.context.getSharedPreferences(filePath, PRIVATE_MODE)
        themeModePref = preference.getBoolean(DARK_MODE, false)

        if (themeModePref != darkModeCurrent) {
            changeTheme()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_darkmode -> {
                changeTheme()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun setThemeMode() {
        val preference: SharedPreferences = layoutInflater.context.getSharedPreferences(filePath, PRIVATE_MODE)
        val editor = preference.edit()
        editor.putBoolean(DARK_MODE, darkModeCurrent)
        editor.commit()
    }

    private fun changeTheme() {
        if (darkModeCurrent) {
            darkModeCurrent = false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            darkModeCurrent = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        setThemeMode()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu!!.findItem(R.id.menu_darkmode)
        if (darkModeCurrent) {
            item.title = "Light Mode"
        } else {
            item.title = "Dark Mode"
        }

        return super.onPrepareOptionsMenu(menu)
    }

}