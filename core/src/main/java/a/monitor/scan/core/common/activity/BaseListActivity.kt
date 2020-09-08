package a.monitor.scan.core.common.activity

import a.monitor.scan.core.R
import a.monitor.scan.core.common.adapter.BaseRecyclerAdapter
import a.monitor.scan.core.common.view.ClassicalFooter
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_base_list.*

/**
@author:jacky
@Date:2020/9/8
默认含有RecyclerView的可下拉刷新，上拉加载（可隐藏）Activity基类
 **/
open class BaseListActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.layout_base_list
    }


    //初始化view
    override fun initView() {
        super.initView()
        mRecyclerView.layoutManager = getLayoutManager()
        mRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN)
        mRecyclerView.setLoadMoreView(ClassicalFooter(this))
    }



    @SuppressLint("WrongConstant")
    private fun getLayoutManager(): RecyclerView.LayoutManager? {
        val layoutManager : LinearLayoutManager = LinearLayoutManager(this)
        layoutManager.orientation = OrientationHelper.VERTICAL
        return layoutManager
    }

    /**
     * 设置RecyclerView 的Adapter适配器
     * @param adapter 设置的Adapter，必须是BaseRecyclerAdapter的子类
     */
    protected fun <T> setAdapter(adapter: BaseRecyclerAdapter<T>) {
        mRecyclerView.adapter = adapter
    }

    /**
     * 设置刷新控件的颜色
     */
    protected fun setRefreshColorSchemeColors(@ColorInt vararg colors: Int) {
        mRefreshLayout.setColorSchemeColors(*colors)
    }
    /**
     * 设置刷新控件的颜色
     */
    protected fun setColorSchemeResources(@ColorRes vararg colorIds: Int) {
        mRefreshLayout.setColorSchemeResources(*colorIds)
    }

    /**
     * 设置刷新控件是否显示
     */
    protected fun isRefresh(isRefresh: Boolean) {
        mRefreshLayout.isRefreshing = false
    }

    /**
     * 刷新完成
     */
    protected fun refreshComplete() {
        mRefreshLayout.isRefreshing = false
    }

    /**
     * 刷新数据，子类实现该方法，进行数据请求
     */
    protected open fun refreshDatas() {

    }

    /**
     * 加载更多完成
     */
    protected fun loadMoreComplete() {
        mRecyclerView.loadMoreComplete(true)
    }

    /**
     * 设置是否可以加载更多
     */
    protected fun canLoadMore(canLoadMore: Boolean) {
        mRecyclerView.loadMoreEnable = canLoadMore
    }

    /**
     * 设置是否还有更多的数据
     */
    protected fun hasLoadMore(hasLoadMore: Boolean) {
        mRecyclerView.hasMore = hasLoadMore
    }

    /**
     * 加载新数据，子类实现该方法，进行数据请求
     */
    protected open fun loadMoreDatas() {

    }



}