package com.muthohhari.visityogyakarta.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.muthohhari.visityogyakarta.*
import com.muthohhari.visityogyakarta.detail.Detail
import com.muthohhari.visityogyakarta.models.Data
import com.muthohhari.visityogyakarta.models.Pariwisata
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*
import retrofit2.Response


class HomeFragment : Fragment(), ViewHome {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: AdapterHome
    private lateinit var presenter: PresenterHome
    private var listData: MutableList<Data> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return UI {
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )
                relativeLayout {
                    lparams(matchParent, matchParent){

                    }
                    progressBar = progressBar {
                    }.lparams(wrapContent, wrapContent) {
                        centerInParent()
                    }
                    recyclerView = recyclerView {
                        layoutManager = LinearLayoutManager(ctx)
                    }.lparams(matchParent, wrapContent) {
                    }
                }
            }

        }.view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = AdapterHome(listData) { partItem: Data -> itemClick(partItem) }
        recyclerView.adapter = adapter
        presenter = PresenterHome(this)
        presenter.getData()
        swipeRefresh.onRefresh {
            listData.clear()
            presenter.getData()
        }
    }

    private fun itemClick(partItem: Data) {
        startActivity<Detail>(Detail.EXTRADATA to partItem)
    }

    override fun showProgressbar() {
        progressBar.visible()
        recyclerView.invisible()
    }

    override fun hideProgressbar() {
        progressBar.invisible()
        recyclerView.visible()
    }

    override fun onSuccess(respon: Response<Pariwisata>) {
        swipeRefresh.isRefreshing = false
        listData.clear()
        listData.addAll(respon.body()!!.data)
        adapter.notifyDataSetChanged()
    }

    override fun onError(throwable: Throwable) {
        toast("Error : "+throwable)
    }

}
