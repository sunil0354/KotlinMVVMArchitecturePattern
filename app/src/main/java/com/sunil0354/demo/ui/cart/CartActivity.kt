package com.sunil0354.demo.ui.cart

import android.os.Bundle
import com.sunil0354.demo.R
import com.sunil0354.demo.databinding.ActivityCartBinding
import com.sunil0354.demo.ui.base.BaseActivity
import com.sunil0354.demo.utils.FragmentUtils

class CartActivity : BaseActivity<ActivityCartBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.activity_cart
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentUtils.replaceFragment(this, CartFragment(), R.id.frameLayout, false)
    }
}