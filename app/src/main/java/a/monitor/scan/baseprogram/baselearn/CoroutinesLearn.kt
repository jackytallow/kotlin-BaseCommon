package a.monitor.scan.baseprogram.baselearn

import android.os.Looper
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.custom.async

/**
@author:jacky
@Date:2020/9/21 协程
 **/
class CoroutinesLearn {


    //统计一下协程大概有3种方式，一个runBlocking，一个launch，一个async



    //第一种方式：runBlocking运行阻塞

    fun runBlockingCoroutines() {

        println("is mainUiThread?"+(Thread.currentThread() == Looper.getMainLooper().thread))
        println("start")
        runBlocking {
            println("is mainThread" + (Thread.currentThread() == Looper.getMainLooper().thread))
            println("delay start")
            delay(2000)
            println("delay end")
        }
    }

    //第二种：launch 目前版本貌似用不了
    fun launchCoroutines() {
        println("is mainUiThread? " + (Thread.currentThread() == Looper.getMainLooper().thread))
        println("start")
    }

    //第三种方式：async异步方式

    fun asyncCoroutines() {
        println("is mainUIThread?" + (Thread.currentThread() == Looper.getMainLooper().thread))
        println("start")
        async {
            println("is MainUIThread?" + (Thread.currentThread() == Looper.getMainLooper().thread))
            println("测试延迟开始")
            //delay(timeMillis = 20000)
            println("测试延迟结束")
        }
    }




}