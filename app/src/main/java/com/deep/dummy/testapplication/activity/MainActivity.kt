package com.deep.dummy.testapplication.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.betacode.free.snappystory.model.Comment
import com.betacode.free.snappystory.model.StoryModel
import com.betacode.free.snappystory.view.SnappyStoryListener
import com.deep.dummy.testapplication.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val comments = arrayListOf(
            Comment("Deep", "Hi! There!"),
            Comment("Tolat", "Hey")
        )
        snap_view.load(
            arrayListOf(
                StoryModel(
                    R.drawable.badge_crown, 3, comments
                ),
                StoryModel(R.drawable.badge_reward, 3, comments),
                StoryModel(R.drawable.bg_no_item_cactus, 4, comments),
                StoryModel(R.drawable.bg_no_item_city, 3, comments),
                StoryModel(R.drawable.badge_crown, 3, comments)
            ),
            object : SnappyStoryListener {
                override fun onAllFinished() {
                }

                override fun onFinished(index: Int) {
                }

                override fun setImageFor(index: Int, story: StoryModel?, imageView: ImageView) {
                    // Extra Functionality
                }

                override fun onStart() {
                    // pre-load images
                }
            }
        )
    }

}