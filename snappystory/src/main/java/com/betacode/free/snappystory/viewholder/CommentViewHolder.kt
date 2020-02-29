package com.betacode.free.snappystory.viewholder

import com.betacode.free.snappystory.R
import com.betacode.free.snappystory.base.BaseViewHolder
import com.betacode.free.snappystory.databinding.ViewCommentBinding
import com.betacode.free.snappystory.model.Comment

class CommentViewHolder(private val binding: ViewCommentBinding) : BaseViewHolder<Comment?>(R.layout.view_comment, binding) {

    override fun apply(data: Comment?, position: Int) {
        super.apply(data, position)
        if (data == null) return
        binding.commentData = data
    }

}