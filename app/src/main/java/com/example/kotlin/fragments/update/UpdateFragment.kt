package com.example.kotlin.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlin.R
import com.example.kotlin.databinding.FragmentUpdateBinding
import com.example.kotlin.fragments.SharedViewModel
import com.example.kotlin.data.models.ToDoData
import com.example.kotlin.utils.NotificationHelper
import com.example.kotlin.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_update.*


class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mTodoViewModel: ToDoViewModel by viewModels()

    private var _binding:FragmentUpdateBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Data bindings
        _binding = FragmentUpdateBinding.inflate(inflater,container,false)
        binding.args = args


        //set Menu
        setHasOptionsMenu(true)

        //spinner item selected listener
        binding.currentPrioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete ->  confirmItemRemoval()
        }
        return super.onOptionsItemSelected(item)
    }



    private fun updateItem() {
        val title = current_title_et.text.toString()
        val description = current_description_et.text.toString()
        val getPriority = current_priorities_spinner.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(title, description)
        if (validation) {

            //update current item
            var updatedItem = ToDoData(
                args.currentItem.id,
                title,
                mSharedViewModel.parsePriority(getPriority),
                description

            )

            mTodoViewModel.updateData(updatedItem)
            Toast.makeText(requireContext(), "Successfully updated!!", Toast.LENGTH_SHORT).show()

            //Notification
            NotificationHelper(requireContext(),updatedItem,"Successfully updated  item to database").createNotification()

            //navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all field!!", Toast.LENGTH_SHORT).show()
        }

    }

    //Show alert dialog to confirm removal item
    private fun confirmItemRemoval() {
        var builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.yes)){ _,_ ->
            mTodoViewModel.deleteItem(args.currentItem)
            Toast.makeText(requireContext(),
                "Successfully Removed: ${args.currentItem.title}",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Delete '${args.currentItem.title}' ?" )
        builder.setMessage("Are you sure you want to remove '${args.currentItem.title}'?")
        builder.create().show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}