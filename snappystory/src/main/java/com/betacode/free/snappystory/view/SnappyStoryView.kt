package com.betacode.free.snappystory.view

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.betacode.free.snappystory.R
import com.betacode.free.snappystory.model.StoryModel
import kotlinx.android.synthetic.main.view_snappy_story.view.*
import kotlin.collections.ArrayList


class SnappyStoryView : RelativeLayout {

    private var mContext: Context? = null

    private var currentStoryIndex: Int = 0
    private var currentStory: StoryModel? = null
    private var stories: ArrayList<StoryModel> = arrayListOf()
    private var progressBars: ArrayList<ProgressBar> = arrayListOf()
    private var timeRemaining = 0L
    private var timeFinished = 0L
    private var timer: CountDownTimer? = null
    private var listener: SnappyStoryListener? = null

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        init(context)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context)
    }

    private fun init(context: Context?) {
        mContext = context
        LayoutInflater.from(context).inflate(R.layout.view_snappy_story, this)
    }

    private fun next(storyModel: StoryModel) {
        currentStory = storyModel
        loadImage()
    }

    private fun loadImage() {
        timer = getTimer()
        snap_image.setImageResource(currentStory?.image ?: 0)
        listener?.setImageFor(currentStoryIndex, currentStory, snap_image)
        snap_image.setOnTouchListener { _, event ->
            if (event?.action == MotionEvent.ACTION_DOWN) {
                timer?.cancel()
            }
            if (event?.action == MotionEvent.ACTION_UP) {
                timer = getTimer(timeRemaining)
                timer?.start()
            }
            true
        }
        timer?.start()
    }

    private fun getTimer(time: Long = 0) = object :
        CountDownTimer(if (time > 0) time else currentStory?.time?.times(1000.toLong()) ?: 0, 50) {
        override fun onFinish() {
            if (currentStoryIndex < stories.size - 1) {
                listener?.onFinished(currentStoryIndex)
                currentStoryIndex++
                next(stories[currentStoryIndex])
            } else {
               listener?.onAllFinished()
            }
        }

        override fun onTick(millisUntilFinished: Long) {
            timeRemaining = millisUntilFinished
            val numerator =
                (currentStory?.time?.times(1000.toLong()) ?: 0) - millisUntilFinished
            timeFinished = numerator
            progressBars[currentStoryIndex].progress =
                (numerator * 100 / (currentStory?.time?.times(1000.toLong()) ?: 0)).toInt()
        }
    }

    // Public Functions

    fun load(stories: ArrayList<StoryModel> = arrayListOf(), listener: SnappyStoryListener? = null) {
        val inflater =
            mContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as? LayoutInflater
        progress_counter.removeAllViews()
        for (story in stories) {
            val view = inflater?.inflate(R.layout.view_progress, null) as ProgressBar
            progressBars.add(view)
            progress_counter.addView(view)
        }
        invalidate()
        progress_counter.visibility = View.VISIBLE
        this.stories = stories
        this.listener = listener
        if (this.stories.isNotEmpty()) {
            listener?.onStart()
            next(stories[0])
            right_side.setOnClickListener {
                if (currentStoryIndex < this.stories.size - 1) {
                    timer?.cancel()
                    this.progressBars[currentStoryIndex].progress = 100
                    currentStoryIndex++
                    next(this.stories[currentStoryIndex])
                }
            }
            left_side.setOnClickListener {
                if (timeFinished > 1000) {
                    timer?.cancel()
                    timer = getTimer(0)
                    timer?.start()
                    return@setOnClickListener
                }
                if (currentStoryIndex > 0) {
                    timer?.cancel()
                    this.progressBars[currentStoryIndex].progress = 0
                    currentStoryIndex--
                    next(this.stories[currentStoryIndex])
                }
            }
        }
    }
}

interface SnappyStoryListener {
    fun onAllFinished()
    fun onFinished(index: Int)
    fun onStart()
    fun setImageFor(index: Int, story: StoryModel?, imageView: ImageView)
}
