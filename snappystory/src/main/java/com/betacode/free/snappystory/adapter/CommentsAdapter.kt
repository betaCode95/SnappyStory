package com.betacode.free.snappystory.adapter

import android.view.LayoutInflater
import com.betacode.free.snappystory.R
import com.betacode.free.snappystory.base.BaseRecyclerAdapter
import com.betacode.free.snappystory.base.BaseViewHolder
import com.betacode.free.snappystory.model.Comment
import com.betacode.free.snappystory.viewholder.CommentViewHolder

class CommentsAdapter : BaseRecyclerAdapter<ArrayList<Comment>?>() {

    override fun createViewHolder(inflater: LayoutInflater, viewType: Int): Int = viewType

    override fun itemCount(): Int = data?.size ?: 0

    override fun itemType(position: Int): Int = R.layout.view_comment

    override fun wrapViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is CommentViewHolder -> holder.apply(data?.get(position), position)
        }
    }
}