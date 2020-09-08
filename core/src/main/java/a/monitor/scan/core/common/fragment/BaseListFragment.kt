package a.monitor.scan.core.common.fragment

import a.monitor.scan.core.R
import a.monitor.scan.core.common.adapter.BaseRecyclerAdapter
import android.annotation.SuppressLint
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import android.view.View
import androidx.recyclerview.widget.OrientationHelper
import kotlinx.android.synthetic.main.layout_base_list.*

/**
 * @description 默认含有RecyclerView的可下拉刷新，上拉加载（可隐藏）Fragment基类
 */
open class BaseListFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.layout_base_list
    }

    /**
     * 初始化View 的相关操作
     */
    override fun initView(contentView: View) {
        super.initView(contentView)
        mRecyclerView.layoutManager = getLayoutManager()
        mRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN)
    }

    /**
     * 初始化View的相关事件
     */
    override fun initListener(contentView:View) {
        super.initListener(contentView)
        mRefreshLayout.setOnRefreshListener {
            refreshDatas()
        }
        mRecyclerView.setLoadMoreListener {
            loadMoreDatas()
        }
    }

    /**
     * 获取recyclerView的布局管理器，子类可重写该方法，来更改布局管理器
     */
    @SuppressLint("WrongConstant")
    protected open fun getLayoutManager(): androidx.recyclerview.widget.RecyclerView.LayoutManager {
        val layoutManager: androidx.recyclerview.widget.LinearLayoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(context)
        //设置为垂直布局，这也是默认的
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