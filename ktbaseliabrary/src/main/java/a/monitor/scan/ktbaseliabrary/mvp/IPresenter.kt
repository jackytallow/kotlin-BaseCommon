package a.monitor.scan.ktbaseliabrary.mvp



interface IPresenter {
    fun onAttachView(baseView: BaseView)

    fun onDestroy()
}