package a.monitor.scan.baseprogram.activity


import a.monitor.scan.baseprogram.net.api.GankServices
import a.monitor.scan.ktbaseliabrary.extension.response
import kotlinx.coroutines.launch

class MainPresenter : MainContract.MainPresenter() {


    override fun changeBaseUrl() {
        mView?.showLoading()
        mainScope.launch {
            var gank = GankServices.instance.getGanHuo().response()
            gank?.let {
                //mView?.showResult(it.first().title!!)
                mView?.showResult(it.first().desc!!)
                mView?.hideLoading()
            }
        }
    }

}