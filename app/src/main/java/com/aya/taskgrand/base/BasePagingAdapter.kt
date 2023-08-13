package com.aya.taskgrand.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aya.taskgrand.R
import com.aya.taskgrand.databinding.ItemDeletedViewBinding
import com.aya.taskgrand.databinding.PagingFooterViewBinding


abstract class BasePagingAdapter<T : BasePaginationParcelable, B : ViewDataBinding>(
    val itemClick: (T) -> Unit = {},
    diffUtilCallBack: DiffUtil.ItemCallback<T> = BaseItemCallback()
) : PagingDataAdapter<T, BaseViewHolder<T>>(diffUtilCallBack) {

    lateinit var mBinding: B
    var mPosition: Int = -1

    var mCurrentList = arrayListOf<T?>()

    companion object {
        const val NORMAL_VIEW = 1
        const val DELETED_VIEW = 0
    }

    @LayoutRes
    abstract fun layoutRes(): Int

    open fun onViewHolderCreated(viewHolder: BaseViewHolder<T>) {}

    open fun onBindItem(item: T, position: Int) {}

    open fun onBindItemBinding(binding: B, item: T, position: Int) {}

    open fun onFullItemClick(position: Int) {
        itemClick.invoke(getItem(position)!!)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.viewType ?: NORMAL_VIEW
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        mBinding = DataBindingUtil.inflate(inflater, layoutRes(), parent, false)
        return when (viewType) {
            DELETED_VIEW -> BaseViewHolder(
                ItemDeletedViewBinding.inflate(inflater, parent, false)
            )
            else -> BaseViewHolder<T>(mBinding).apply {
                onViewHolderCreated(this)
                itemView.setOnClickListener {
                    mPosition = absoluteAdapterPosition
                    onFullItemClick(mPosition)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(getItem(position)!!,position)
        onBindItem(getItem(position)!!, position)
        onBindItemBinding(holder.binding as B, getItem(position)!!, position)
    }


    fun updateItem(item: T) {
        val listOfItems = (snapshot().items as MutableList<T>)
        listOfItems.indexOfFirst { item.unique() == it.unique() }
            .takeIf { it > -1 }?.let { pos ->
                listOfItems[pos] = item
                notifyItemChanged(pos)
            }
    }

    fun removeItem(item: T, isListEmpty: (Boolean) -> Unit = {}) {
        val newItem = item
        newItem.viewType = DELETED_VIEW
        updateItem(newItem)
        val list = (snapshot().items as MutableList<T>)
        val isEmpty = list.filter { it.viewType == DELETED_VIEW }.size == list.size
        isListEmpty.invoke(isEmpty)
    }

    fun addItem(item: T) {
        val newItem = item
        val listOfItems = (snapshot().items as MutableList<T>)
        listOfItems.add(newItem)
        notifyItemInserted(listOfItems.lastIndex)
    }

      fun setList(newList: List<T?>?, callBack: () -> Unit = {}) {
        val listOfItems = (snapshot().items as MutableList<T>)
        listOfItems.addAll((newList ?: emptyList()) as Collection<T>)
      //  submitData(listOfItems)

    }


}

class FooterLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<FooterLoadStateAdapter.FooterLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: FooterLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): FooterLoadStateViewHolder {
        return FooterLoadStateViewHolder.create(parent, retry)
    }

    class FooterLoadStateViewHolder(
        private val binding: PagingFooterViewBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMsg.text = loadState.error.localizedMessage
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
            binding.errorMsg.isVisible = loadState is LoadState.Error
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): FooterLoadStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.paging_footer_view, parent, false)
                val binding = PagingFooterViewBinding.bind(view)
                return FooterLoadStateViewHolder(binding, retry)
            }
        }
    }
}



class BaseItemCallbackk<T : BaseParcelable> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) =
        oldItem.unique().toString() == newItem.unique().toString()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) =
        oldItem == newItem
}

class BaseViewHolderr<T>(val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T, position: Int) {
       // binding.setVariable(BR.model, item)
        binding.executePendingBindings()
    }
}



interface BasePaginationParcelablee : BaseParcelable {
    var viewType: Int
}
