package a.monitor.scan.ktbaseliabrary.proxy

import a.monitor.scan.ktbaseliabrary.event.MessageEvent

/**
 * Ui加载配置
 */
interface ShowUIProxy {

    fun <T> parseResponseFailMessage(messageEvent: MessageEvent<T>)

    fun showLoading()

    fun hideLoading()

    /**
     * 网络错误等之类的错误提示
     * @dayParam errorMessage
     */
    fun showError(errorMessage: String)
}