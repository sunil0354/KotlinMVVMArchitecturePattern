package com.sunil0354.demo.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunil0354.demo.R
import com.sunil0354.demo.databinding.FragmentPaymentBinding
import com.sunil0354.demo.repository.model.TestList
import com.sunil0354.demo.repository.model.TestListItem
import com.sunil0354.demo.ui.base.BaseFragment
import com.sunil0354.demo.ui.main.MainAdapter
import kotlinx.android.synthetic.main.fragment_payment.*
import kotlinx.android.synthetic.main.include_top_header.*

class PaymentFragment : BaseFragment<FragmentPaymentBinding>(), (TestListItem) -> Unit {
    private lateinit var adapter: MainAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(PaymentViewModel::class.java)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_payment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        setObservers()
    }

    private fun initViews() {
        tvtitle?.text = getString(R.string.order_history)
        rlCart?.visibility = View.INVISIBLE
        ivBack?.visibility = View.VISIBLE
        adapter = MainAdapter(this, false)
        rvItems.adapter = adapter
        rvItems.layoutManager = LinearLayoutManager(context)
        dataBinding?.tvHome?.setOnClickListener {
            (context as CartActivity).onBackPressed()
        }
        ivBack.setOnClickListener {
            (context as CartActivity).onBackPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setObservers() {
        viewModel.getDataFromRoom().observe(this, Observer {
            adapter.replaceItems(TestList().apply {
                addAll(it)
            })
            dataBinding?.progressBar?.visibility = View.GONE
        })
    }

    override fun invoke(p1: TestListItem) {
    }

    override fun onDestroy() {
        viewModel.deleteAllDataFromRoom()
        super.onDestroy()
    }
}