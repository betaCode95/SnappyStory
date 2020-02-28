

# SnappyStory

![Build Status](https://travis-ci.org/ChuckerTeam/chucker.svg?branch=master) ![License](https://img.shields.io/github/license/ChuckerTeam/Chucker.svg) [![PRs Welcome](https://img.shields.io/badge/PRs-welcome-orange.svg)](http://makeapullrequest.com)

A module that helps in modularizing Snapchat's story-like UI

* [Getting Started](#getting-started-)
* [Features](#features-)
* [Configure](#configure-)
* [FAQ](#faq-)
* [Contributing](#contributing-)

## Getting Started 👣

SnappyStory is not yet distributed locally. Will be uploaded on Bintray

```groovy
repositories {
    maven { url "https://jitpack.io" }
}
```

```groovy
dependencies {
  // [To Be Added Soon]
}
```

To start using SnappyStory

```kotlin
        snap_view.load(
            arrayListOf(
                StoryModel(R.drawable.badge_crown, 5),
                StoryModel(R.drawable.badge_reward, 5),
                StoryModel(R.drawable.bg_no_item_cactus, 5),
                StoryModel(R.drawable.bg_no_item_city, 5),
                StoryModel(R.drawable.badge_crown, 5)
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

```

In you Layout XML

```xml
<com.betacode.free.snappystory.view.SnappyStoryView 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/snap_view"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

**That's it!** 🎉 Start Showing Stories in your App

## Features 🧰

[To Be Added]

## Configure 🎨

[To Be Added]

## FAQ ❓

* **Why is it not working for me?** - dependency issue maybe, create an issue if it doesn't work
* **Will it support other types of media?** - Yes, all kinds but that will take time

## Contributing 🤝

**Looking for contributors! Don't be shy.** 😁 Feel free to open issues/pull requests to help me improve this project.