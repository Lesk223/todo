package com.example.myapplication.screen.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.APP
import com.example.myapplication.R
import com.example.myapplication.adapter.TaskAdapter
import com.example.myapplication.databinding.FragmentTaskRecBinding
import com.example.myapplication.databinding.FragmentTaskRecBinding.inflate
import com.example.myapplication.model.Tasks
import com.example.myapplication.screen.addnote.AddNoteViewModel
import com.example.myapplication.screen.start.StartViewModel


class TaskRec(id:Int) : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters

    lateinit var binding: FragmentTaskRecBinding
    lateinit var recyclerView:RecyclerView
    lateinit var adapter:TaskAdapter
    var idA=id



override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    binding = inflate(layoutInflater, container, false)
    return binding.root

}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {
        recyclerView = binding.recyc
        adapter = TaskAdapter(idA)
        recyclerView.adapter = adapter
        val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        viewModel.getAllTask(idA).observe(viewLifecycleOwner) { tasks ->
            adapter.setList(tasks.asReversed(), idA)
            binding.floatButtonRec.setOnClickListener(this)
            val itemSwipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
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
            val swap = ItemTouchHelper(itemSwipe)
            swap.attachToRecyclerView(recyclerView)

        }
    }

    override fun onClick(v: View?) {
        val bundle=Bundle()
        bundle.putSerializable("note", idA)
APP.navController.navigate(R.id.action_recFrag_to_addTask,bundle)
    }

    companion object {

        fun checkTask(tasks: Tasks) {
            val viewModel = ViewModelProvider(APP).get(AddNoteViewModel::class.java)
            viewModel.update(tasks){}
            if (tasks.check==true) {
                AlertDelete(tasks)
            }

        }

     private fun AlertDelete(tasks: Tasks) {
         val viewModel = ViewModelProvider(APP).get(StartViewModel::class.java)
         val builder = AlertDialog.Builder(APP, R.style.DeleteAlert)
         builder.setTitle("Удаление")
         builder.setMessage("Вы выполнили задачу , желаете её удалить?")
         builder.setPositiveButton("Да") { dialog, which ->
             viewModel.delete(tasks) {}
         }
         builder.setNegativeButton("Нет") { dialog, which ->
         }
         builder.show()

    }}

    private fun showDialog(viewHolder: RecyclerView.ViewHolder){
        val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        val builder=AlertDialog.Builder(APP,R.style.DeleteAlert)
        builder.setTitle("Удаление")
        builder.setMessage("Действительно хотите удалить задачу '${adapter.taskNote[viewHolder.adapterPosition].name}'")
        builder.setPositiveButton("Да"){dialog,which->
            val position =viewHolder.adapterPosition
            //viewModel.delete( adapter.listNote[position]){}
            viewModel.delete(adapter.taskNote[position]){}

            //     viewModel.delCascade(adapter.listNote[position].id)

        }
        builder.setNegativeButton("Нет"){dialog,which->
            val position =viewHolder.adapterPosition
            adapter.notifyItemChanged(position)


        }
        builder.show()



    }
}
