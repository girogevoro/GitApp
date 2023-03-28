package com.gitapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.girogevoro.gitapp.databinding.ActivityMainBinding
import com.gitapp.mvp.presenter.CounterNumber
import com.gitapp.mvp.presenter.Presenter
import com.gitapp.mvp.view.MainView
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private var presenter: Presenter = Presenter(this)
    private var buttonMap: EnumMap<CounterNumber, Button> = EnumMap(CounterNumber::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initButton()
    }

    private fun initButton() {
        buttonMap[CounterNumber.FIRST] = binding.btnCounter1
        buttonMap[CounterNumber.SECOND] = binding.btnCounter2
        buttonMap[CounterNumber.THIRD] = binding.btnCounter3

        val listener = View.OnClickListener { view ->
            val counterNumber =
                buttonMap.firstNotNullOf { it.takeIf { mapEntry -> mapEntry.value == view } }.key
            presenter.counterClick(counterNumber)
        }

        buttonMap.forEach { it.value.setOnClickListener(listener) }
    }

    override fun setButtonText(counterNumber: CounterNumber, text: String) {
        buttonMap[counterNumber]?.text = text
    }
}