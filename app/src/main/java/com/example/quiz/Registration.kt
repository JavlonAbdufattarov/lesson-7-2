package com.example.quiz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.quiz.R.id
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Registration.newInstance] factory method to
 * create an instance of this fragment.
 */
class Registration : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false);
        // Inflate the layout for this fragment
        val btn_Date = view.findViewById<Button>(R.id.btn_date);
        val btn_Save =view.findViewById<Button>(R.id.btn_save)
         val  edittext1 = view.findViewById<EditText>(R.id.editText);


        btn_Date.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener { selection ->
                // Преобразование даты в человекоориентированный вид
                val simpleDateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
                val date = Date(selection)
                val formattedDate = simpleDateFormat.format(date)

                // Показ отформатированной даты в Snackbar
                Snackbar.make(view, "Выбранная дата: $formattedDate", Snackbar.LENGTH_LONG).show()
            }
            datePicker.show(parentFragmentManager, "DATE_PICKER")
        }
        btn_Save.setOnClickListener {

            val name = edittext1.text.toString()
            val action = RegistrationDirections.actionRegistrationToQuizFragment(name)
            findNavController().navigate(action)
        }


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Registration.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Registration().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}