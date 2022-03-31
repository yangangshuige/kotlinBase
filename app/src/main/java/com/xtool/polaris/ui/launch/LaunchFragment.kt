package com.xtool.polaris.ui.launch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xtool.polaris.R
import com.xtool.polaris.databinding.FragmentLaunchBinding
import com.xtool.polaris.utils.LogUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class LaunchFragment : Fragment(R.layout.fragment_launch) {
    private lateinit var viewBinding: FragmentLaunchBinding
    private val jumpTime = 3
    private var mDisposable: Disposable? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentLaunchBinding.bind(view)
        mDisposable =
            Flowable.interval(1, TimeUnit.SECONDS).take(jumpTime.toLong()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    LogUtils.d("subscribe$it")
//                    if (jumpTime.toLong() == it) {
//                        findNavController().navigate(
//                            R.id.main_activity,
//                            null,
//                            navOptions { launchSingleTop = true })
//                        requireActivity().finishAfterTransition()
//                    } else {
//                        viewBinding.tvContent.text = "跳过" + (jumpTime - it) + "s"
//                    }
                }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable?.dispose()
    }
}