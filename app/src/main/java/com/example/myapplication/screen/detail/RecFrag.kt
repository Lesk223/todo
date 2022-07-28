package com.example.myapplication.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.APP
import com.example.myapplication.R
import com.example.myapplication.adapter.TaskAdapter
import com.example.myapplication.databinding.FragmentRecBinding
import com.example.myapplication.db.NoteDao
import com.example.myapplication.model.FilesNote
import com.example.myapplication.model.Node
import com.example.myapplication.model.Tasks
import com.example.myapplication.screen.start.StartViewModel
import com.google.android.material.tabs.TabLayout


class RecFrag() : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentRecBinding
    lateinit var tasks: Tasks
 var idCurrent:Int = 0
    var place:Boolean=true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        tab()
      // val firstFrag= TaskRec()
            }

    fun init() {
        idCurrent = arguments?.getSerializable("note") as Int
        if (place == true) {
            var fr = fragmentManager?.beginTransaction()
            fr?.replace(
                R.id.container,
                NodeRec(idCurrent)
            )
            fr?.commit()
        } else{
        var fr = fragmentManager?.beginTransaction()
        fr?.replace(
            R.id.container,
            TaskRec(idCurrent)
        )
        fr?.commit()
            binding.tab.getTabAt(1)?.select()


    }
         //bundle.putSerializable("note", c)
        val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        viewModel.initDatabase()

       //  viewModel.getAllTask(currentNote.id).observe(viewLifecycleOwner, { taskNotes ->
         //    adapter.setList(taskNotes,currentNote.id)
           //  binding.floatButtonRec.setOnClickListener(this)


    }
    companion object {
        fun clickNote(node: Node): Node {
            val bundle = Bundle()
            bundle.putSerializable("note", node)
            APP.navController.navigate(R.id.action_recFrag_to_detailFragment, bundle)
            return node

        }
    }
    fun tab(){

        binding.tab.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        var fr = fragmentManager?.beginTransaction()
                        fr?.replace(R.id.container,
                            NodeRec(idCurrent)
                        )
                        fr?.addToBackStack(null);
                        fr?.commit()
                        place=true
                    }
                    1 -> {
                        var fr = fragmentManager?.beginTransaction()
                        fr?.replace(R.id.container,TaskRec(idCurrent))
                        fr?.addToBackStack(null);
                        fr?.commit()
                        place=false
                        // fragment = ParkingHistoricFragment()
                     //  Toast.makeText(APP, "fragm 3", Toast.LENGTH_SHORT).show()
                    }


                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
               // Toast.makeText(APP, "fragm 2 ", Toast.LENGTH_SHORT).show()
            }


            /* override fun onTabSelected(tab: TabLayout.Tab) {
                var fragment: Fragment? = null
                when (tab.position) {
                    0 -> {
                        fragment = ParkingMapFragment()
                        Toast.makeText(activity, "fragm 1 ", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        fragment = ParkingHistoricFragment()
                        Toast.makeText(activity, "fragm 2 ", Toast.LENGTH_SHORT).show()
                    }
                }
                val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
                transaction.add(R.id.simpleFrameLayout, fragment)
                transaction.commit()
            }
        })*/
        })}

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }


}













