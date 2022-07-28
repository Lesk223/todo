package com.example.myapplication.screen.addnote

import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.APP
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAddNoteBinding
import com.example.myapplication.model.Node
import com.example.myapplication.screen.detail.RecFrag
import com.example.myapplication.screen.start.StartViewModel
import java.text.SimpleDateFormat
import java.util.*


class AddNoteFragment(): Fragment(), Toolbar.OnMenuItemClickListener, View.OnClickListener {
   lateinit var binding: FragmentAddNoteBinding
   var idA:Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentAddNoteBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idA= arguments?.getSerializable("note") as Int
        getdate()
        binding.toolbar.menu.removeItem(R.id.deleteNode)
        binding.toolbar.setOnMenuItemClickListener(this)
        binding.toolbar.setNavigationOnClickListener(this)
    }
    @SuppressLint("SimpleDateFormat")
    fun getdate(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        return currentDate
       // Log.d("date",currentDate.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView=item.itemId
        when(itemView){
            R.id.adds->Toast.makeText(context,"add",Toast.LENGTH_SHORT).show()
        }
        return false
    }


    private fun addNode() {
        val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
            val title = binding.AddTitle.text.toString()

            //idCurrent = arguments?.getSerializable("note") as Int
            val descriptio = binding.AddDescript.text.toString()
            val time = getdate().toString()
            if (title.isBlank()){
                Toast.makeText(context,"Введите название!",Toast.LENGTH_SHORT).show()
            }else {

                viewModel.insert(Node(ownerId = idA, name = title, date = time, description = descriptio)){}
               APP.navController.popBackStack()
            }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        var itemView= item?.itemId
        when(itemView){
            R.id.adds->Toast.makeText(context,"add",Toast.LENGTH_SHORT).show()
            R.id.save-> addNode()
        }
        return false
    }
    private fun ALertSave(){
        val viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
        val builder= AlertDialog.Builder(APP,R.style.DeleteAlert)
        builder.setTitle("Сохранить изменения")
        builder.setPositiveButton("Да"){dialog,which->
        if (binding.AddTitle.text.isBlank()){
            Toast.makeText(APP,"Введите название",Toast.LENGTH_SHORT).show()
        }else {
            addNode()
            APP.navController.popBackStack()
        }
        }
        builder.setNegativeButton("Нет"){dialog,which->
            activity?.onBackPressed()
        }
        builder.show()
    }
    override fun onClick(v: View?) {

ALertSave()        //val bundle = Bundle()
        //bundle.putSerializable("note",idA)
        //APP.navController.navigate(R.id.action_addNoteFragment_to_recFrag,bundle)
    }
}








