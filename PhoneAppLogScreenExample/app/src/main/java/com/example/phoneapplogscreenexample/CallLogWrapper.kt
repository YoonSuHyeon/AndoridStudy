package com.example.phoneapplogscreenexample


import android.view.View
import android.widget.ImageView
import android.widget.TextView


class CallLogWrapper(root: View) {
    var personImageView:ImageView = root.findViewById(R.id.main_item_person) as ImageView
    lateinit var nameView:TextView
    lateinit var dateView:TextView
    lateinit var  dialerImageView: ImageView

    init {
        nameView = root.findViewById(R.id.main_item_name) as TextView
        dateView = root.findViewById(R.id.main_item_date) as TextView
        dialerImageView = root.findViewById(R.id.main_item_dialer) as ImageView

    }
}