package com.example.myapplication.screen.addnote

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.APP
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAddNoteBinding
import com.example.myapplication.model.NoteModel
import com.example.myapplication.model.Tasks
import java.text.SimpleDateFormat
import java.util.*


class AddNoteFragment : Fragment() {
   lateinit var binding: FragmentAddNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentAddNoteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getdate()

    }
    @SuppressLint("SimpleDateFormat")
    fun getdate(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        return currentDate
       // Log.d("date",currentDate.toString())
    }



    private fun init() {
        val viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
        binding.btnUp.setOnClickListener {
            val title = binding.AddTitle.text.toString()
            //val descriptio = binding.AddDescript.text.toString()
            val time = getdate().toString()
            if (title.isBlank()){
                Toast.makeText(context,"Введите название!",Toast.LENGTH_SHORT).show()
            }else {
                viewModel.insert(NoteModel(title = title, time = time)) {}
                APP.navController.navigate(R.id.action_addNoteFragment_to_startFragment)
            }}

    }
    }









