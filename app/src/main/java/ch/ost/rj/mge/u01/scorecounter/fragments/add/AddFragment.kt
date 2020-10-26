package ch.ost.rj.mge.u01.scorecounter.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ch.ost.rj.mge.u01.scorecounter.R
import ch.ost.rj.mge.u01.scorecounter.fragments.SharedViewModel
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.Color
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.PlayerData
import ch.ost.rj.mge.u01.scorecounter.fragments.data.viewmodel.PlayerViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private val mPlayerViewModel: PlayerViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        setHasOptionsMenu(true)

        view.color_spinner.onItemSelectedListener = mSharedViewModel.listener

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add) {
            insertDataToDatabase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase() {
        val mName = playerName_edit.text.toString()
        val mColor = color_spinner.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mName)
        if(validation) {
            val newData = PlayerData(
                0,
                mName,
                mSharedViewModel.parseColor(mColor),
            )
            mPlayerViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Succesfully added!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_SHORT).show()
        }
    }

}