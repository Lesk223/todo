package com.example.myapplication.screen.detail

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.DatePickerDialog.*
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.APP
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAddTaskBinding
import com.example.myapplication.model.FilesNote
import com.example.myapplication.model.Tasks
import com.example.myapplication.screen.addnote.AddNoteViewModel
import com.example.myapplication.screen.start.StartViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*


class AddTask : Fragment(), Toolbar.OnMenuItemClickListener, View.OnClickListener {
    lateinit var binding: FragmentAddTaskBinding
    var timeData: String = ""
    var dateData: String = ""
    var status: String = ""
    var idA: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        RadioGroup()
        idA = arguments?.getSerializable("note") as Int
        binding.toolbar.menu.removeItem(R.id.adds)
        binding.toolbar.setOnMenuItemClickListener(this)
        binding.toolbar.setNavigationOnClickListener(this)
        binding.toolbar.menu.findItem(R.id.deleteNode)
            .setIcon(R.drawable.ic_baseline_notifications_active_24)
        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("SimpleDateFormat")
    fun getdate(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = sdf.format(Date())
        return currentDate
        // Log.d("date",currentDate.toString())
    }


    private fun init() {
        dateData = getdate()
        binding.dateEnter.setText(getdate())
        binding.dateEnter.setOnClickListener {
            calendar()
        }
        binding.editTextDate.setOnClickListener {
            showCustomTimePicker()
        }
        binding.toolbar.setOnClickListener {
            Log.d("Status", status)

        }
        val viewmodel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
    }

    fun showCustomTimePicker() {
        val myCalender = Calendar.getInstance()
        val hour = myCalender[Calendar.HOUR_OF_DAY]
        val minute = myCalender[Calendar.MINUTE]
        val myTimeListener =
            OnTimeSetListener { view, hourOfDay, minute ->
                if (view.isShown) {
                    myCalender[Calendar.HOUR_OF_DAY] = hourOfDay
                    myCalender[Calendar.MINUTE] = minute
                }
                var minutes = minute.toString()
                var hourOf = hourOfDay.toString()
                if (hourOfDay < 10) {
                    hourOf = "0" + hourOfDay
                }
                if (minute < 10) {
                    minutes = "0" + minute
                }
                binding.editTextDate.setText(hourOf + ":" + minutes)
                timeData = (hourOf + ":" + minutes)
                Log.d("Timedata", timeData)
            }
        val timePickerDialog = TimePickerDialog(
            activity,
            THEME_HOLO_DARK,
            myTimeListener,
            hour,
            minute,
            true
        )
        timePickerDialog.setTitle("Выберите время")
        timePickerDialog.show()

    }

  /*  private fun Alarm() {

        setAlarm(calendar.timeInMillis)

*/
    private fun showDialog(){
        val viewmodel=ViewModelProvider(this).get(AddNoteViewModel::class.java)
            val  builder= MaterialAlertDialogBuilder(APP, R.style.AlertDialog)
            //     val builder= MatAlertDialog.Builder(APP, R.style.AlertDialog)
            val inflater=layoutInflater
            val dialogLayout=inflater.inflate(R.layout.fragment_add_task,null)
            val editText=dialogLayout.findViewById<EditText>(R.id.editTextTextPersonName)
            with(builder){
                setTitle("Введите название")
                 // if (check)  showdate()

                setPositiveButton("OK") { dialog, which ->
                    //currentNote.title=editText.text.toString()
                    val notemodel = FilesNote(title = editText.text.toString())
                    viewmodel.insert(notemodel) {}
                }
                setView(dialogLayout)
                show()


            }
        }
    private  fun calendar(){
       val value:Calendar= Calendar.getInstance()
        val selectedYear = value.get(Calendar.YEAR)
        val selectedMonth =value.get(Calendar.MONTH)
        val selectedDayOfMonth = value.get(Calendar.DAY_OF_MONTH)
        val dateSetListener =
            OnDateSetListener { view, year,monthOfYear, dayOfMonth ->
                val monthOfYea=monthOfYear+1
                var month=monthOfYea.toString()
                if (monthOfYear<10){
                    month="0"+monthOfYea
                }
                var day=dayOfMonth.toString()
                if (dayOfMonth<10){
                  day="0"+dayOfMonth
                }
                binding.dateEnter.setText(day+"/"+month+"/"+year.toString())
                dateData=day+"/"+month+"/"+year.toString()
            }

        val datePickerDialog = DatePickerDialog(
            APP, dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth
        )
        Log.d("timesZ", value.timeInMillis.toString())
        datePickerDialog.show()
    }

   /* private fun setAlarm(timeInMillis: Long) {
        val alarmManager =APP.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(APP, MyAlarm::class.java)
        val pendingIntent = PendingIntent.getBroadcast(APP, 0, intent, 0)
        alarmManager.setRepeating(
            AlarmManager.RTC,
            timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText(APP, "Alarm is set", Toast.LENGTH_SHORT).show()
    }
    private class MyAlarm : BroadcastReceiver() {
        override fun onReceive(
            context: Context,
            intent: Intent
        ) {
            Log.d("Alarm Bell", "Alarm just fired")
        }
    }
*/

    private fun addTask(){
        val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        if (binding.editTextTextPersonName2.text.isBlank()){
            Toast.makeText(APP,"Введите название",Toast.LENGTH_SHORT).show()

        }else{
viewModel.insert(Tasks(name = binding.editTextTextPersonName2.text.toString(), date = dateData+" "+timeData, category = status, ownerId =idA )){}
            APP.navController.popBackStack()
        }}
    private fun RadioGroup(){
        binding.radiogroup.setOnCheckedChangeListener { group, checkedId ->
           when(checkedId){
               R.id.radioButton1->status=binding.radioButton1.text.toString()
               R.id.radioButton2->status=binding.radioButton2.text.toString()
               R.id.radioButton3->status=binding.radioButton3.text.toString()
           }
        }
    }
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        var itemView= item?.itemId
        when(itemView){
            R.id.save-> {
                addTask()

            }
            R.id.deleteNode->{Toast.makeText(APP,"Уведомления скоро появятся",Toast.LENGTH_SHORT).show()}
        }
        return false
    }
    private fun ALertSave(){
        val viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
        val builder= AlertDialog.Builder(APP,R.style.DeleteAlert)
        builder.setTitle("Сохранить изменения")
        builder.setPositiveButton("Да"){dialog,which->
            if (binding.editTextTextPersonName2.text.isBlank()){
                Toast.makeText(APP,"Введите название",Toast.LENGTH_SHORT).show()
            }else {
                addTask()
                val bundle = Bundle()
                bundle.putSerializable("place",true)
                APP.navController.navigate(R.id.action_addTask_to_recFrag,bundle)
            }
        }
        builder.setNegativeButton("Нет"){dialog,which->
            activity?.onBackPressed()
        }
        builder.show()
    }
    override fun onClick(v: View?) {
           ALertSave()    }
}



