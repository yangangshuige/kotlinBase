package androidx.fragment.app

import android.widget.Toast
import com.xtool.polaris.widget.LoadingDialogHelper

/**
 * Fragment扩展
 */
fun Fragment.showLoading(msg: String = "正在加载") {
    LoadingDialogHelper.showLoading(this.context, msg)
}

fun Fragment.hideLoading() {
    LoadingDialogHelper.hideLoading(this.context)
}

fun Fragment.showToast(msg: String) {
    Toast.makeText(requireContext(),
        msg,
        Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(msg: Int) {
    Toast.makeText(requireContext(),
        getString(msg),
        Toast.LENGTH_SHORT).show()
}
