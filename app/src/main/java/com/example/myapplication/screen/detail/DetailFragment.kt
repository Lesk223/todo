package com.example.myapplication.screen.detail

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.APP
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.model.NoteModel
import com.example.myapplication.model.Tasks
import com.example.myapplication.screen.addnote.AddNoteViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    lateinit var currentNote:NoteModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDetailBinding.inflate(layoutInflater,container,false)
        currentNote=arguments?.getSerializable("note")as NoteModel

return binding.root   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    init()
    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
    private fun init() {
        val viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
        binding.textViewDescript.text = currentNote.description.toEditable()
        binding.textViewBack.setOnClickListener {
            APP.navController.navigate(R.id.action_detailFragment_to_recFrag)
        }
        binding.saveView.setOnClickListener {
            currentNote.description = binding.textViewDescript.text.toString()
            viewModel.update(currentNote) {}
            APP.navController.navigate(R.id.action_detailFragment_to_recFrag)
        }
        binding.deleteView.setOnClickListener {
            viewModel.delete(currentNote) {}
            APP.navController.navigate(R.id.action_detailFragment_to_recFrag)
        }


    }

}