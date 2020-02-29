package com.betacode.free.snappystory.common

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.betacode.free.snappystory.base.BaseRecyclerAdapter

fun <D> RecyclerView.setup(adapter: BaseRecyclerAdapter<D>, data: D) {
    if (this.adapter == null) {
        this.adapter = adapter
        this.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
    }
    this.scheduleLayoutAnimation()
    adapter.updateData(data)
}