package a.monitor.scan.ktbaseliabrary.proxy

import a.monitor.scan.ktbaseliabrary.event.MessageEvent


/**
 * EventBus接口
 */

interface EventBusProxy {
    //注册
    fun register(subscriber: Any?)

    //取消注册
    fun unregister(subscriber: Any?)

    fun <T> post(event: MessageEvent<T>)

    fun <T> postSticky(event: MessageEvent<T>)

    fun <T> removeSticky(event: MessageEvent<T>)

    fun clear()

    fun setOnEventBusListener(onEventBusListener: OnEventBusListener)

    interface OnEventBusListener {
        fun <T> onMessage(messageEvent: MessageEvent<T>)
    }
}