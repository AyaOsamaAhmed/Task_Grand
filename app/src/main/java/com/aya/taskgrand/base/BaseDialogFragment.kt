package com.aya.taskgrand.base

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.annotation.ColorRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.aya.taskgrand.R
import com.aya.taskgrand.core.extension.*
import com.aya.taskgrand.features.activity.MainActivity

abstract class BaseDialogFragment<B : ViewDataBinding, VM : ViewModel> :
    DialogFragment() {

    abstract fun onFragmentReady()

    protected abstract val mViewModel: VM

    private var _binding: B? = null
    lateinit var binding: B

    open val isDismissible: Boolean = false
    private var removeBgOnDismiss: Boolean = true

    @ColorRes
    open val opacityBackgroundRes: Int = R.color.transparent_gray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            R.style.FullScreenDialogStyle
        )
    }

    fun setRemoveBackgroundOnDismiss(remove: Boolean) {
        this.removeBgOnDismiss = remove
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindView()
        binding = _binding!!
        if (isDismissible) {
            dialog?.setCanceledOnTouchOutside(isDismissible)
            dismissAtView(binding.root)
            isCancelable = isDismissible
        }
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  _binding?.setVariable(BR.viewModel, mViewModel)
        setBackgroundOpacity(opacityBackgroundRes)
        onFragmentReady()
    }

    private fun setBackgroundOpacity(opacityBackgroundRes: Int) {
        castToActivity<MainActivity> {
        //    it?.setOpacityBackground(it.binding.opacityBackground, opacityBackgroundRes)
        }
    }

    override fun onResume() {
        // Store access variables for window and blank point
        val window: Window? = dialog!!.window
        // Set the width of the dialog proportional to 75% of the screen width
        window?.setGravity(Gravity.CENTER)
        super.onResume()
    }

    fun closeFragment() {
        findNavController().navigateUp()
    }

    fun showToast(message: String?) {
        context?.showAppToast(message)
    }

    fun showErrorDialog(message: String?) {
        context?.showErrorDialog(message)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    override fun onDismiss(dialog: DialogInterface) {
        if (removeBgOnDismiss)
            Handler(Looper.getMainLooper()).post {
                setBackgroundOpacity(0)
            }

        super.onDismiss(dialog)
    }

    private fun dismissAtView(view: View) {
        view.setOnClickListener {
            dismiss()
        }
    }

}


