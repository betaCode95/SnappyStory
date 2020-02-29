package com.betacode.free.snappystory.base

import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T>(val viewType: Int, itemView: ViewDataBinding): RecyclerView.ViewHolder(itemView.root) {

    @CallSuper
    open fun apply(data: T, position: Int) {
        apply(data, position, null)
    }

    @CallSuper
    open fun apply(data: T, position: Int, listener: Any?) {}

}