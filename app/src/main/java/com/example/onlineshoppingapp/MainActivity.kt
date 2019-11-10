package com.example.onlineshoppingapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.TransactionDetails
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BillingProcessor.IBillingHandler {
    override fun onBillingInitialized() {
    }

    override fun onPurchaseHistoryRestored() {
    }

    override fun onProductPurchased(productId: String, details: TransactionDetails?) {
    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {
    }

    lateinit var bp :BillingProcessor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bp=BillingProcessor(this , null, this)

        // this button allow us to purches our product
        btn_book.setOnClickListener{
            bp.purchase(this, "android test purches ")
        }
        btn_book2.setOnClickListener{
            bp.purchase(this, "android test purching 2")
        }
        btn_book3.setOnClickListener {
            bp.purchase(this," android test result 3 ")
        }
    }

    // so in this case we are trying first on the bp handler if that doesn.t work deal with otherotion
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (!bp.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        if(bp!=null)
            bp.release()
        super.onDestroy()
    }

}

