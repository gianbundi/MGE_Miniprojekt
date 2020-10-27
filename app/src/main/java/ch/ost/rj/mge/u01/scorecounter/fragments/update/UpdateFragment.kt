package ch.ost.rj.mge.u01.scorecounter.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ch.ost.rj.mge.u01.scorecounter.R
import ch.ost.rj.mge.u01.scorecounter.fragments.SharedViewModel
import ch.ost.rj.mge.u01.scorecounter.fragments.data.models.PlayerData
import ch.ost.rj.mge.u01.scorecounter.fragments.data.viewmodel.PlayerViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mPlayerViewModel: PlayerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        setHasOptionsMenu(true)

        view.current_playerName_edit.setText(args.currentItem.name)
        view.current_color_spinner.setSelection(mSharedViewModel.parseColorToInt(args.currentItem.color))
        view.current_color_spinner.onItemSelectedListener = mSharedViewModel.listener
        view.current_score_edit.setText(args.currentItem.score.toString())
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    fun updateItem() {
        val name = current_playerName_edit.text.toString()
        val getColor = current_color_spinner.selectedItem.toString()
        val getScore = current_score_edit.text.toString().toInt()

        val validation = mSharedViewModel.verifyDataFromUser(name)
        if(validation) {
            val updatedItem = PlayerData(
                args.currentItem.id,
                name,
                mSharedViewModel.parseColor(getColor),
                getScore
            )
            mPlayerViewModel.updateData(updatedItem)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mPlayerViewModel.deleteItem(args.currentItem)
            Toast.makeText(
                requireContext(),
                "Successfully Removed: ${args.currentItem.name}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete '${args.currentItem.name}'?")
        builder.setMessage("Are you sure you want to remove '${args.currentItem.name}'?")
        builder.create().show()
    }

}