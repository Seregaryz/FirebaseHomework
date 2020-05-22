package com.kpfu.itis.firebasehomework.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.kpfu.itis.firebasehomework.App
import com.kpfu.itis.firebasehomework.R
import com.kpfu.itis.firebasehomework.di.Injector
import com.kpfu.itis.firebasehomework.di.Injector.appComponent
import com.kpfu.itis.firebasehomework.di.Injector.authenticationComponent
import com.kpfu.itis.firebasehomework.model.Racer
import com.kpfu.itis.firebasehomework.ui.presenter.ListPresenter
import com.kpfu.itis.firebasehomework.ui.view.list.RacerAdapter
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Provider
import javax.inject.Inject

class ListActivity : MvpAppCompatActivity(), ListView {

    lateinit var dialog: AlertDialog

    @Inject
    lateinit var presenterProvider: Provider<ListPresenter>

    private val presenter: ListPresenter by moxyPresenter {
        presenterProvider.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Injector.plusAuthenticationComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initListeners()
        initRecycler()
        initAdd()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.toolbar, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        presenter.causeCrash();
        return true
    }

    private fun initListeners() {
        btn_signOut.setOnClickListener { presenter.signOut() }
        btn_show_dialog.setOnClickListener{
            presenter.openDialog()
        }
    }

    override fun initAdd() {
        presenter.initAd(findViewById(R.id.adView))
    }

    override fun openDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.fragment_dialog, null)
        val builder = this.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
        }
        dialog = builder.show()
        dialogView.btn_add_dialog.setOnClickListener {
            presenter.closeDialog()
            val name = dialogView.et_racer_name.text.toString()
            val description = dialogView.et_racer_team.text.toString()
            val index = dialogView.et_racer_index.text.toString().toInt() - 1
            presenter.updateList(name,description,index)
        }
        dialogView.btn_cancel_dialog.setOnClickListener {
            presenter.closeDialog()
        }
    }

    override fun closeDialog() {
        dialog.dismiss()
    }

    override fun initRecycler() {
        rv_racers.apply {
            this.layoutManager = LinearLayoutManager(this@ListActivity)
            this.adapter = presenter.initRecycler()
        }
        presenter.setRecyclerViewItemTouchListener(rv_racers)
        //rv_racers.setHasFixedSize(true)
    }

    override fun goToAuth() {
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

}
