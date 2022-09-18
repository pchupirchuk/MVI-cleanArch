package com.simple.mvi.common

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModelForClass

/**
 * Created by Rim Gazzah on 8/19/20.
 **/
abstract class BaseActivity<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState,
        VM : BaseViewModel<INTENT, ACTION, STATE>>(modelClass: Class<VM>) :
    AppCompatActivity(), IViewRenderer<STATE> {
    private lateinit var viewState: STATE
    val mState get() = viewState

    private val viewModel: VM by viewModelForClass(modelClass.kotlin)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initUI()
        viewModel.state.observe(this, {
            viewState = it
            render(it)
        })
        initDATA()
        initEVENT()
    }


    @LayoutRes
    abstract fun getLayoutResId(): Int
    abstract fun initUI()
    abstract fun initDATA()
    abstract fun initEVENT()
    fun dispatchIntent(intent: INTENT) {
        viewModel.dispatchIntent(intent)
    }
}