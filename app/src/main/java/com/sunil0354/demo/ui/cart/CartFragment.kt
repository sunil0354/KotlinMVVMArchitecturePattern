package com.sunil0354.demo.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunil0354.demo.R
import com.sunil0354.demo.databinding.FragmentCartBinding
import com.sunil0354.demo.repository.model.TestList
import com.sunil0354.demo.repository.model.TestListItem
import com.sunil0354.demo.ui.base.BaseFragment
import com.sunil0354.demo.ui.main.MainAdapter
import com.sunil0354.demo.utils.FragmentUtils
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.include_top_header.*

class CartFragment : BaseFragment<FragmentCartBinding>(), (TestListItem) -> Unit {
    private lateinit var adapter: MainAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(CartViewModel::class.java)
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_cart
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        setObservers()
    }

    private fun initViews() {
        tvtitle?.text = getString(R.string.cart)
        rlCart?.visibility = View.INVISIBLE
        ivBack?.visibility = View.VISIBLE
        adapter = MainAdapter(this, true)
        rvItems.adapter = adapter
        rvItems.layoutManager = LinearLayoutManager(context)
        dataBinding?.tvPay?.setOnClickListener {
            FragmentUtils.replaceFragment(
                (context as CartActivity),
                PaymentFragment(),
                R.id.frameLayout,
                false
            )
        }
        ivBack.setOnClickListener {
            (context as CartActivity).onBackPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setObservers() {
        viewModel.getDataFromRoom().observe(this, Observer {
            if (it.isNotEmpty()) {
                var amount = 0
                for (i in it.indices) {
                    amount += it[i].minPrice
                }
                tvPay?.text = "Pay $${amount}"
                tvPay?.visibility = View.VISIBLE
                tvEmptyCart?.visibility = View.GONE
                adapter.replaceItems(TestList().apply {
                    addAll(it)
                })
            } else {
                tvEmptyCart?.visibility = View.VISIBLE
                tvPay?.visibility = View.GONE
            }
            dataBinding?.progressBar?.visibility = View.GONE
        })
    }

    override fun invoke(p1: TestListItem) {
        viewModel.removeData(p1.itemId)
    }
}