package com.kpfu.itis.firebasehomework.ui.presenter

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdView
import com.kpfu.itis.firebasehomework.model.Racer
import com.kpfu.itis.firebasehomework.service.SignInServiceImpl
import com.kpfu.itis.firebasehomework.ui.view.ListView
import com.kpfu.itis.firebasehomework.ui.view.list.RacerAdapter
import moxy.MvpPresenter
import javax.inject.Inject

class ListPresenter@Inject constructor(
    private var service: SignInServiceImpl
) : MvpPresenter<ListView>()  {

    private var list: ArrayList<Racer> = getDataSource()
    private lateinit var adapter: RacerAdapter

    fun signOut(){
        service.signOut()
    }

    fun openDialog(){
        viewState.openDialog()
    }

    fun closeDialog(){
        viewState.closeDialog()
    }

    fun updateList(name: String, team: String, index: Int){
        val listSize = list.size
        var newIndex = index
        if (index > listSize ?:0) {
            newIndex = listSize ?:0
        }
        list.add(newIndex, Racer(name, team))
        list.let { adapter.updateList(it) }
    }

    fun initRecycler(): RacerAdapter{
        adapter = RacerAdapter(list) { racer ->
            delete(racer)
        }
        return adapter
    }

    fun initAd(adView: AdView){
        service.initAdd(adView)
    }

    fun causeCrash() {
        Log.w("Crashlytics", "Crash button clicked");
        throw NullPointerException("Fake null pointer exception")
    }

    fun setRecyclerViewItemTouchListener(recyclerView: RecyclerView) {
        val itemTouchCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder1: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val index = viewHolder.adapterPosition
                list.removeAt(index)
                list.let { adapter.updateList(it) }
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        recyclerView.addItemDecoration(itemTouchHelper)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    fun authenticate(){
        if (!service.authUser()){
            viewState.goToAuth()
        }
    }

    fun delete(racer: Racer) {
        list.remove(racer)
        list.let { adapter.updateList(it) }
    }

    private fun getDataSource(): ArrayList<Racer> = arrayListOf(
        Racer(
            "Daniil Kvyat", "Scuderia Toro Rosso"
        ),
        Racer(
            "Max Verstappen","Red Bull Racing"
        ),
        Racer(
            "Lewis Hamilton",  "Mercedes"
        ),
        Racer(
            "Robert Kubica", "Williams"
        ),
        Racer(
            "Romain Grosjean", "Haas"
        ),
        Racer(
            "Charles Leclerc", "Scuderia Ferrari"
        ),
        Racer(
            "Sergio Perez", "Racing Point"
        ),
        Racer(
            "Kimi Räikkönen", "Alfa Romeo"
        ),
        Racer(
            "Daniel Ricciardo", "Renault"
        ),
        Racer(
            "Carlos Sainz", "McLaren"
        )

    )
}