package com.betacode.free.snappystory.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.betacode.free.snappystory.R
import com.betacode.free.snappystory.databinding.ViewCommentBinding
import com.betacode.free.snappystory.viewholder.CommentViewHolder

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseViewHolder<*>>() {

    open var data: T? = null

    abstract fun createViewHolder(inflater: LayoutInflater, viewType: Int): Int
    abstract fun itemCount(): Int
    abstract fun itemType(position: Int): Int
    abstract fun wrapViewHolder(holder: BaseViewHolder<*>, position: Int)

    @CallSuper
    protected open fun computeValue(data: T?) {
    }

    fun updateData(data: T?) {
        this.data = data
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = createViewHolder(LayoutInflater.from(parent.context), viewType)
        val viewBinding: ViewDataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), view, parent, false)
        return viewToViewHolder(viewBinding)
    }

    override fun getItemViewType(position: Int): Int {
        return itemType(position)
    }

    override fun getItemCount(): Int = itemCount()

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) =
        wrapViewHolder(holder, position)

    private fun viewToViewHolder(viewBinding: ViewDataBinding): BaseViewHolder<*> =
        when (viewBinding) {
            is ViewCommentBinding -> CommentViewHolder(viewBinding)
            else -> BaseViewHolder<Any>(R.layout.view_comment, viewBinding)
        }
}