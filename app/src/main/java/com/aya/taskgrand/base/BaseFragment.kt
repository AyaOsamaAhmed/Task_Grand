package com.aya.taskgrand.base

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.aya.taskgrand.core.extension.*
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel> : Fragment() {

    open fun mPageTitle(): String? = null

    /**
     * first: background res
     * second: height
     * */
    open fun mPageToolbarBackground(): Pair<Int, Int>? = null
    open val mTitleGravity: Int = Gravity.CENTER_VERTICAL

    abstract fun onFragmentReady()

    protected abstract val mViewModel: VM

    private var _binding: B? = null
    lateinit var binding: B
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindView()
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         onFragmentReady()
    }


    fun closeFragment() {
        hideKeyboard()
        activity?.onBackPressed()
    }

    fun showProgress(show: Boolean = true) {
        castToActivity<BaseActivity<*, *>> {
            it?.baseShowProgress?.set(show)
        }
    }

    fun showToast(message: String?) {
        context?.showAppToast(message)
    }

    fun setSnackBar(view : View , message: String?) : Snackbar?{
      return  context?.setSnackBar(view , message)
    }


    override fun onDestroyView() {
        showProgress(false)
        _binding = null
        super.onDestroyView()
    }

}
