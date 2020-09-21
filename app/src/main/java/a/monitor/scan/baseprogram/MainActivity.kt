package a.monitor.scan.baseprogram

import a.monitor.scan.baseprogram.activity.GankActivity
import a.monitor.scan.baseprogram.sorted.SortedActivity
import a.monitor.scan.baseprogram.video.VideoActivity
import a.monitor.scan.core.common.activity.BaseActivity
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {




    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initListener() {
        sortList.setOnClickListener {
            startForActivity<SortedActivity>()
        }


        videoList.setOnClickListener {
            startForActivity<VideoActivity>()
        }

        base_url.setOnClickListener {
            startActivity(Intent(this, GankActivity::class.java))
        }




    }



}
