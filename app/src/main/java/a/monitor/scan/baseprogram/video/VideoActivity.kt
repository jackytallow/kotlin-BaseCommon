package a.monitor.scan.baseprogram.video

import a.monitor.scan.baseprogram.R
import a.monitor.scan.baseprogram.sorted.ModelVideoTest
import a.monitor.scan.core.common.activity.BaseActivity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.julive.adapter.animators.findFirstCompletelyVisibleItemPosition
import com.julive.adapter.animators.firstAnimation
import com.julive.adapter.core.*
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import kotlinx.android.synthetic.main.activity_video_list.*
import org.jetbrains.anko.backgroundColorResource

/**
@author:jacky
@Date:2020/9/9
 **/
class VideoActivity : BaseActivity() {


    private val mp4_a = "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4";
    private val mp4_b = "http://vfx.mtime.cn/Video/2019/03/13/mp4/190313094901111138.mp4";

    private val gsyVideoOptionBuilder = GSYVideoOptionBuilder().apply {

        setIsTouchWiget(false)
        setVideoTitle("title")
        setCacheWithPlay(true)
        setRotateViewAuto(true)
        setLockLand(false)
        setShowFullAnimation(true)
        setReleaseWhenLossAudio(false)

    }


    private val mListAdapter =
        listAdapter {
            (0..10).forEach { _ ->
                add(

                    layoutViewModelDsl(
                        R.layout.item_test_video_view,
                        ModelVideoTest(mp4_b, mp4_a, -1)
                    ) {

                        val player = getView<StandardGSYVideoPlayer>(R.id.player)
                        val title = getView<TextView>(R.id.tv_title)
                        val subTitle = getView<TextView>(R.id.tv_subTitle)

                        player.fullscreenButton.setOnClickListener {
                            player.startWindowFullscreen(itemView.context, false, true)
                        }

                        player.isAutoFullWithSize = true

                        onViewAttachedToWindow {
                            firstAnimation()
                        }

                        onBindViewHolder {

                            val model = getModel<ModelVideoTest>()

                            if (it.isEmpty()) {

                                title.text = "title$adapterPosition"
                                subTitle.text = "sutTitle"

                                gsyVideoOptionBuilder.apply {
                                    if (adapterPosition % 2 == 0) {
                                        setUrl(model?.url)
                                    } else {
                                        setUrl(model?.url2)
                                    }
                                    setPlayPosition(adapterPosition)
                                    setPlayTag(adapterPosition.toString())
                                    setGSYVideoProgressListener { progress, secProgress, currentPosition, duration ->
                                        model?.seekOnStart = currentPosition.toLong()
                                    }
                                    setThumbImageView(ImageView(itemView.context).apply {
                                        backgroundColorResource = R.color.colorAccent
                                        layoutParams = player.layoutParams
                                    })

                                }.build(player)

                            }

                            it.takeIf {
                                it.isNotEmpty() && it[0] == 1
                            }?.also {
                                player.seekOnStart = model?.seekOnStart ?: -1
                                player.startPlayLogic()
                            }

                        }
                    })
            }
        }


    override fun getLayoutId(): Int {
        return R.layout.activity_video_list
    }

    override fun initView() {
        mListAdapter.into(rv_list_video)
    }

    override fun initListener() {
        var currentPlayingPosition = 0

        rv_list_video.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        val position = rv_list_video.findFirstCompletelyVisibleItemPosition()
                        Log.d("ScrollChange", "position:$position")
                        if (currentPlayingPosition != position) {
                            currentPlayingPosition = position
                            mListAdapter.notifyItemChanged(position, 1)
                        }
                    }
                }
            }
        })


        rv_list_video.postDelayed({
            mListAdapter.notifyItemChanged(currentPlayingPosition,1)
        },1000)
    }


    override fun onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        GSYVideoManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }



}