package com.sunil0354.demo.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunil0354.demo.R
import com.sunil0354.demo.databinding.ActivityMainBinding
import com.sunil0354.demo.repository.model.TestList
import com.sunil0354.demo.repository.model.TestListItem
import com.sunil0354.demo.ui.base.BaseActivity
import com.sunil0354.demo.ui.cart.CartActivity
import com.sunil0354.demo.utils.ConnectivityUtil
import com.sunil0354.demo.utils.ToastUtil
import com.sunil0354.demo.utils.extensions.getViewModel
import com.sunil0354.demo.utils.extensions.load
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_networkerror.*
import kotlinx.android.synthetic.main.include_top_header.*
import java.util.*


class MainActivity() : BaseActivity<ActivityMainBinding>(),
        (TestListItem) -> Unit {

    private lateinit var adapter: MainAdapter
    private var mTestList: TestList? = null
    private var mTestlistItem: List<TestListItem> = emptyList()

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    private val viewModel by lazy {
        getViewModel<MainViewModel, ActivityMainBinding>()
    }

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        setObservers()
        hitAPI()
    }

    private fun setObservers() {
        viewModel.getDataFromRoom().observe(this, Observer {
            mTestlistItem = it
            if (mTestlistItem.isNotEmpty()) {
                rlCart.visibility = View.VISIBLE
                tvCartCount?.text = mTestlistItem.size.toString()
            } else {
                rlCart.visibility = View.INVISIBLE
            }
        })
    }

    private fun hitAPI() {
        if (ConnectivityUtil.isConnected(this)) {
            rlInternetError?.visibility=View.GONE
            viewModel.getNewsArticlesFromServer().observe(this, Observer {
                when {
                    it.status.isLoading() -> {
                        dataBinding?.progressBar?.visibility = View.VISIBLE
                    }
                    it.status.isSuccessful() -> {
                        dataBinding?.progressBar?.visibility = View.GONE
                        it.load {
                            it?.let {
                                mTestList = it
                                adapter.replaceItems(mTestList!!)
                            }
                        }
                    }
                    it.status.isError() -> {
                        dataBinding?.progressBar?.visibility = View.GONE
                        ToastUtil.showNormalToast(this, getString(R.string.unknown_error))
                    }
                }
            })
        } else {
            dataBinding?.progressBar?.visibility = View.GONE
            rlInternetError?.visibility=View.VISIBLE
        }
    }

    private fun initViews() {
        adapter = MainAdapter(this, false)
        rvItems.adapter = adapter
        rvItems.layoutManager = LinearLayoutManager(this)
        //Listner
        dataBinding?.etSearch?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (mTestList != null) {
                    val tempTestList = TestList()
                    for (i in 0 until mTestList!!.size) {
                        if (mTestList!![i].itemName!!.toLowerCase(Locale.getDefault()).startsWith(
                                dataBinding?.etSearch?.text?.trim().toString().toLowerCase(
                                    Locale.getDefault()
                                )
                            )
                        ) {
                            tempTestList.add(mTestList!!.get(i))
                        }
                    }
                    adapter.replaceItems(tempTestList)
                }
            }
        })
        rlCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
        tvRetry.setOnClickListener {
            hitAPI()
        }
    }

    override fun invoke(p1: TestListItem) {
        viewModel.addDataInRoom(p1)
        for (i in mTestlistItem.indices) {
            if (mTestlistItem[i].itemId.equals(p1.itemId)) {
                ToastUtil.showNormalToast(this, getString(R.string.you_can_add))
                return
            }
        }
        ToastUtil.showNormalToast(this, getString(R.string.item_has_been))
    }
}