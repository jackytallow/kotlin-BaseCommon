package a.monitor.scan.baseprogram

import a.monitor.scan.core.common.activity.BaseActivity
import a.monitor.scan.core.common.activity.BaseListActivity
import a.monitor.scan.core.rxrequest.activity.BaseRxActivity
import a.monitor.scan.core.rxrequest.activity.BaseRxListActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : BaseListActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

}
