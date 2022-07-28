package com.example.myapplication.screen.start


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.APP
import com.example.myapplication.R
import com.example.myapplication.adapter.FileAdapter
import com.example.myapplication.databinding.FragmentStartBinding
import com.example.myapplication.model.FilesNote
import com.example.myapplication.model.NodeWithTask
import com.example.myapplication.screen.addnote.AddNoteViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class StartFragment : Fragment() {
    lateinit var binding: FragmentStartBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FileAdapter
    lateinit var currentNote:NodeWithTask

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        showDialog()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        viewModel.initDatabase()
        recyclerView = binding.recyclerView
        adapter = FileAdapter()
        recyclerView.adapter = adapter
        viewModel.getAllNotes().observe(viewLifecycleOwner) { listNotes ->
            adapter.setList(listNotes.asReversed())
            if (adapter.itemCount == 0) {
                binding.imagestart.setImageResource(R.drawable.ic_baseline_folder_24)
            } else {
                binding.imagestart.setImageDrawable(null)
            }
        }
        // binding.floatButton.setOnClickListener {

         //   APP.navController.navigate(R.id.action_startFragment_to_addNoteFragment)

       // }
        val itemSwipe=object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                showDialog(viewHolder)
            }

        }
        val swap=ItemTouchHelper(itemSwipe)
        swap.attachToRecyclerView(recyclerView)
    }
private fun showDialog(viewHolder: RecyclerView.ViewHolder){
    val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
    val builder=AlertDialog.Builder(APP,R.style.DeleteAlert)
    builder.setTitle("Удаление")
    builder.setMessage("Действительно хотите удалить папку '${adapter.listNote[viewHolder.adapterPosition].title}'")
    builder.setPositiveButton("Да"){dialog,which->
        val position =viewHolder.adapterPosition
       //viewModel.delete( adapter.listNote[position]){}
        viewModel.delete(adapter.listNote[position]){}
        viewModel.deleteAllNodes(adapter.listNote[position].id){}
        viewModel.deleteAllTasks(adapter.listNote[position].id){}

        //     viewModel.delCascade(adapter.listNote[position].id)

    }
    builder.setNegativeButton("Нет"){dialog,which->
        val position =viewHolder.adapterPosition
        adapter.notifyItemChanged(position)


    }
    builder.show()



}
    companion object {
        fun clickNote(id:Int): Int {
            val bundle = Bundle()

            bundle.putSerializable("note", id)
            APP.navController.navigate(R.id.action_startFragment_to_recFrag, bundle)
            return id

        }
    }
private fun showDialog(){
    val viewmodel=ViewModelProvider(this).get(AddNoteViewModel::class.java)
    binding.floatButton.setOnClickListener{
   val  builder=MaterialAlertDialogBuilder(APP,R.style.AlertDialog)
        //     val builder= MatAlertDialog.Builder(APP, R.style.AlertDialog)
        val inflater=layoutInflater
        val dialogLayout=inflater.inflate(R.layout.edittext,null)
        val editText=dialogLayout.findViewById<EditText>(R.id.editTextTextPersonName)
        with(builder){
            setTitle("Введите название папки")
            setPositiveButton("OK") { dialog, which ->
                //currentNote.title=editText.text.toString()
                val notemodel = FilesNote(title = editText.text.toString())
                    viewmodel.insert(notemodel) {}
            }
                setView(dialogLayout)
                show()


        }
        }
}


    }






