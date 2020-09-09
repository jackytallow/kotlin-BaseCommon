package a.monitor.scan.baseprogram.sorted

import a.monitor.scan.baseprogram.R
import android.widget.TextView
import android.widget.Toast
import com.julive.adapter.animators.firstAnimation
import com.julive.adapter.animators.updateAnimation
import com.julive.adapter.core.*
import com.julive.adapter.sorted.SortedListAdapter
import com.julive.adapter.sorted.SortedModel
import kotlin.random.Random

/**
@author:jacky
@Date:2020/9/9
 Model 扩展DiffModel 兼容DiffUtil
 **/
/**
 * Model
 * // 扩展DiffModel  兼容DiffUtil
 */
data class ModelTest(var title: String, var subTitle: String) : SameModel {
    override fun <T : SameModel> getChangePayload(newItem: T): Any? {
        return null
    }

    override var uniqueId: String = title
}

data class ModelVideoTest(val url:String,val url2:String,var seekOnStart:Long)

/**
 * sortedId 排序用
 * title 作为uniqueId ，RecyclerView ItemView 更新的时候，唯一值，注意列表是可以出现一样的uniqueId的，
 * 如果想更新请调用Adapter updateItem 这样才能保证列表中uniqueId唯一
 */
data class SortedModelTest(
    val sortedId: Int, var title: String, var subTitle: String,
    override var uniqueId: String = title
) : SortedModel {
    override fun <T : SortedModel> compare(model: T): Int {
        if (sortedId > (model as? SortedModelTest)?.sortedId ?: 0) return 1
        if (sortedId < (model as? SortedModelTest)?.sortedId ?: 0) return -1
        return 0
    }
}

class SortedItemViewModelTest : LayoutViewModel<SortedModelTest>(R.layout.item_test_2) {
    init {
        onCreateViewHolder {
            itemView.setOnClickListener {
                val vm = getViewModel<SortedItemViewModelTest>()
                vm?.model?.subTitle = "刷新自己${Random.nextInt(100)}"
                vm?.let { it1 -> getAdapter<SortedListAdapter>()?.set(adapterPosition, it1) }
            }
            onViewAttachedToWindow {
                firstAnimation(R.anim.item_animation_from_bottom)
                updateAnimation()
            }
        }
    }

    override fun bindVH(viewHolder: DefaultViewHolder, payloads: List<Any>) {
        viewHolder.getView<TextView>(R.id.tv_title).text = model?.title
        viewHolder.getView<TextView>(R.id.tv_subTitle).text = model?.subTitle
    }
}