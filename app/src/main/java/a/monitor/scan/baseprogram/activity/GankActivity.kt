package a.monitor.scan.baseprogram.activity

import a.monitor.scan.baseprogram.R
import a.monitor.scan.ktbaseliabrary.mvp.BaseMvpActivity
import a.monitor.scan.ktbaseliabrary.utils.contentresolver.ContentResolverManager
import a.monitor.scan.ktbaseliabrary.utils.permission.checkPermissions
import android.Manifest
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_gank.*


class GankActivity : BaseMvpActivity<MainPresenter>(),
    MainContract.MainView {


    override fun getLayoutId(): Int {
        return R.layout.activity_gank
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {

//        btChange.setOnClickListener {
//            mPresenter?.changeBaseUrl()
//        }
//        btPermission.setOnClickListener {
//            requestPer()
//        }
        testUrl.setOnClickListener {
            mPresenter?.changeBaseUrl()
        }

        testPermission.setOnClickListener {
            requestPer()
        }

    }



    private fun requestPer() {
        checkPermissions(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO) {
            tvShow.text = if (it) "有权限" else "没有权限"
        }
    }

    override fun showResult(result: String) {
        tvShow.text = result
    }

    override fun onDestroy() {
        super.onDestroy()
        ContentResolverManager.unregister(this)
    }

}
