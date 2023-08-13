package com.aya.taskgrand.base

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aya.taskgrand.core.extension.asConfig
import timber.log.Timber


abstract class BaseAdapter<T : BaseParcelable, B : ViewDataBinding>(
    private val itemClick: (T) -> Unit = {}
) : ListAdapter<T, BaseViewHolder<T>>(BaseItemCallback<T>().asConfig()) {

    var mCurrentList = arrayListOf<T?>()
    lateinit var adapterContext: Context
    var mPosition: Int = 0

    lateinit var mBinding: B

    @LayoutRes
    abstract fun layoutRes(): Int

    open fun onViewHolderCreated(viewHolder: BaseViewHolder<T>) {}
    open fun onBindItemHolder(holder: BaseViewHolder<T>, position: Int, item: T) {}
    open fun onBindItemBinding(binding: B, item: T, position: Int) {}
    open fun onFullItemClick() {}
    open fun onFullItemClick(position: Int) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        adapterContext = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding: B? = DataBindingUtil.inflate(inflater, layoutRes(), parent, false)
        if (binding != null) {
            mBinding = binding
        } else throw java.lang.Exception("binding is null")
        return BaseViewHolder<T>(binding).apply {
            onViewHolderCreated(this)
            itemView.setOnClickListener {
                mPosition = bindingAdapterPosition
                onFullItemClick()
                onFullItemClick(mPosition)
                itemClick.invoke(getItem(mPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(getItem(position), position)
        onBindItemHolder(holder, position, getItem(position))
        onBindItemBinding(holder.binding as B, getItem(position), position)
    }

    fun removeItem(item: T?, isListEmpty: (Boolean) -> Unit = {}) {
        item?.let {
            mCurrentList.remove(it)
            submitList(mCurrentList.toMutableList())
            isListEmpty(mCurrentList.size == 0)
        }
    }

    fun updateList(newList: List<T>) {
        mCurrentList.addAll(newList)
        submitList(mCurrentList.toMutableList())
    }

    fun setList(newList: List<T?>?, callBack: () -> Unit = {}) {
        mCurrentList.clear()
        mCurrentList.addAll(newList ?: emptyList())
        submitList(mCurrentList.toMutableList(), callBack)
    }

    /*override fun submitList(list: List<T?>?) {
        super.submitList(list?.let { ArrayList(it) })
    }*/

    fun addItemToList(item: T?, isAdded: (Boolean) -> Unit = {}) {
        item?.let {
            mCurrentList.add(item)
            submitList(mCurrentList.toMutableList())
            isAdded.invoke(true)
        } ?: Timber.e("item is null")
    }

    fun updateItem(item: T?) {
        item?.let {
            mCurrentList[mPosition] = item
//              notifyItemChanged(mPosition) /* this not working */
            notifyItemRangeChanged(0, mCurrentList.size)
        } ?: Timber.e("item is null")
    }

    fun clearCurrentList() {
        mCurrentList.clear()
        submitList(mCurrentList.toMutableList())
    }

    fun isEmptyData() = mCurrentList.isEmpty()
}

class BaseItemCallback<T : BaseParcelable> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) =
        oldItem.unique().toString() == newItem.unique().toString()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) =
        oldItem == newItem
}

class BaseViewHolder<T>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: T, position: Int) {
      //  binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}



interface BasePaginationParcelable : BaseParcelable {
    var viewType: Int
}

interface BaseSelectionParcelable : BaseParcelable {
    fun itemId(): Int
    override fun unique(): Any = itemId()
    fun itemImage(): String
    fun title(): String
    var isSelected: Boolean
}
