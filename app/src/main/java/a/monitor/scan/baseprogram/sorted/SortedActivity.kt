package a.monitor.scan.baseprogram.sorted

import a.monitor.scan.baseprogram.R
import a.monitor.scan.core.common.activity.BaseActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.julive.adapter.core.into
import com.julive.adapter.sorted.SortedListAdapter
import kotlinx.android.synthetic.main.activity_array_list.*
import kotlinx.android.synthetic.main.include_button_bottom.*
import kotlin.random.Random

/**
@author:jacky
@Date:2020/9/9
 **/
class SortedActivity : BaseActivity() {


    private val mSortedListAdapter by lazy {
        SortedListAdapter()
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_array_list
    }

    override fun initView() {
        supportActionBar?.title = "SortedListAdapter"
        mSortedListAdapter.into(
            rv_list,
            LinearLayoutManager(this)
        )

        rv_list.setItemViewCacheSize(0)
        (0..10).map {
            mSortedListAdapter.add(SortedItemViewModelTest().apply {
                model = SortedModelTest(it,"标题$it", "副标题$it")
            })
        }
    }

    override fun initListener() {
        var index = 100
        btn_left.setText("新增").setOnClickListener {
            // 要想根据uniqueId更新数据，需要调用updateItem方法
            mSortedListAdapter.add(SortedItemViewModelTest().apply {
                model = SortedModelTest(index++, "标题$index", "新增$index")
            })
        }

        btn_middle.setText("删除").setOnClickListener {
            if (mSortedListAdapter.itemCount > 0) {
                val randomInt = Random.nextInt(0, mSortedListAdapter.itemCount)
                mSortedListAdapter.removeAt(randomInt)
            }
        }
        btn_right.setText("替换").setOnClickListener {
            // 根据uniqueId替换 如果sortId不一样就会触发排序
            if (mSortedListAdapter.itemCount > 0) {
                val randomInt = Random.nextInt(0, mSortedListAdapter.itemCount)
                mSortedListAdapter.set(randomInt, mSortedListAdapter.getItem(randomInt).also {
                    it as SortedItemViewModelTest
                    it.model?.subTitle = "替换副标题"
                })
            }
        }
    }




}