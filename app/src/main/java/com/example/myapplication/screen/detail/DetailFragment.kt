package com.example.myapplication.screen.detail

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.APP
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.model.Node
import com.example.myapplication.screen.addnote.AddNoteViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import java.text.SimpleDateFormat
import java.util.*


class DetailFragment : Fragment(), Toolbar.OnMenuItemClickListener, View.OnClickListener {
    lateinit var binding: FragmentDetailBinding
    lateinit var currentNote:Node
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDetailBinding.inflate(layoutInflater,container,false)
        currentNote=arguments?.getSerializable("note")as Node
        binding.toolbar.title=currentNote.name
        binding.toolbar.setOnMenuItemClickListener(this)
        binding.toolbar.setNavigationOnClickListener(this)
return binding.root   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    init()
    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
    private fun init() {
        binding.textViewDescript.text = currentNote.description.toEditable()
        binding.AddTitle.text=currentNote.name.toEditable()


    }
 private fun ALertSave(){
     val viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
     val builder= AlertDialog.Builder(APP,R.style.DeleteAlert)
    builder.setTitle("Сохранить изменения")
    builder.setPositiveButton("Да"){dialog,which->
        getdate()
        currentNote.date=getdate()
        currentNote.name=binding.AddTitle.text.toString()
        currentNote.description=binding.textViewDescript.text.toString()
        viewModel.update(currentNote){}
        APP.navController.popBackStack()
    }
    builder.setNegativeButton("Нет"){dialog,which->
        activity?.onBackPressed()
    }
    builder.show()
}
   private fun AlertDelete(){
        val viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
        val builder= AlertDialog.Builder(APP,R.style.DeleteAlert)
        builder.setTitle("Удаление")
        builder.setMessage("Вы действительно хотите удалить запись?")
        builder.setPositiveButton("Да"){dialog,which->
            viewModel.delete(currentNote) {}
            APP.navController.popBackStack()
        }
        builder.setNegativeButton("Нет"){dialog,which->
        }
        builder.show()

    }

    fun getdate(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        return currentDate
        // Log.d("date",currentDate.toString())
    }
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        val viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)

        var itemView= item?.itemId
        when(itemView) {
            R.id.adds -> Toast.makeText(context, "add", Toast.LENGTH_SHORT).show()
            R.id.save -> {
                getdate()
                currentNote.date = getdate()
                currentNote.name = binding.AddTitle.text.toString()
                currentNote.description = binding.textViewDescript.text.toString()
                viewModel.update(currentNote) {}
                Toast.makeText(APP,"Сохранено",Toast.LENGTH_SHORT).show()
            }
            R.id.deleteNode -> AlertDelete()
        }
        return false
    }

    override fun onClick(v: View?) {

ALertSave()    }

}