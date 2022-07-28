package com.example.myapplication.screen.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.APP
import com.example.myapplication.R
import com.example.myapplication.adapter.NodeAdapter
import com.example.myapplication.databinding.FragmentNodeRecBinding
import com.example.myapplication.model.Tasks
import com.example.myapplication.screen.addnote.AddNoteViewModel
import com.example.myapplication.screen.start.StartViewModel

class NodeRec(id:Int) : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentNodeRecBinding
    lateinit var tasks: Tasks
    lateinit var adapter: NodeAdapter
    val idA:Int=id
    lateinit var recyclerView: RecyclerView
//    private var param2: String? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNodeRecBinding.inflate(layoutInflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }
    fun init() {

recyclerView = binding.recyc
        adapter = NodeAdapter(idA)
        recyclerView.adapter = adapter
        val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        viewModel.getAllNotes().observe(viewLifecycleOwner) { listNotes ->
                    viewModel.getAllNode(idA).observe(viewLifecycleOwner) { nodes ->
              adapter.setList(nodes.asReversed(),idA)
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

             }}

    override fun onClick(v: View?) {
        val bundle=Bundle()
        bundle.putSerializable("note", idA)
        APP.navController.navigate(R.id.action_recFrag_to_addNoteFragment,bundle)

    }
    private fun showDialog(viewHolder: RecyclerView.ViewHolder){
        val viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
        val builder= AlertDialog.Builder(APP,R.style.DeleteAlert)
        builder.setTitle("Удаление")
        builder.setMessage("Действительно хотите удалить заметку '${adapter.listNote[viewHolder.adapterPosition].name}'")
        builder.setPositiveButton("Да"){dialog,which->
            val position =viewHolder.adapterPosition
            //viewModel.delete( adapter.listNote[position]){}
            viewModel.delete(adapter.listNote[position]){}

            //     viewModel.delCascade(adapter.listNote[position].id)

        }
        builder.setNegativeButton("Нет"){dialog,which->
            val position =viewHolder.adapterPosition
            adapter.notifyItemChanged(position)


        }
        builder.show()



    }
}