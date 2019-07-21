package com.maruani.esgi.summaryapplication.module.common

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.maruani.esgi.summaryapplication.module.common.navigator.Navigator

@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<P : BasePresenter<V>, V : BaseView>(@LayoutRes open val layout: Int, val presenter: P) :
    AppCompatActivity(),
    BaseView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.view = this as V
        presenter.navigator = Navigator(this)
        setContentView(layout)

        lifecycle.addObserver(presenter)
    }
}