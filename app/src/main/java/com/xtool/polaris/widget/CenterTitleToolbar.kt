package com.xtool.polaris.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.TextViewCompat

class CenterTitleToolbar @SuppressLint("CustomViewStyleable") @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.toolbarStyle
) : Toolbar(context, attrs, defStyleAttr) {
    private lateinit var tvTextTitle: TextView

    init {
        try {
            val field = Toolbar::class.java
                .getDeclaredField("mTitleTextAppearance")
            field.isAccessible = true

            val titleTextAppearance = field.get(this) as Int

            if (titleTextAppearance != 0) {
                setTitleTextAppearance(context, titleTextAppearance)
            }
        } catch (e: NoSuchFieldException) {
            // ignore
        }

        val a = context.obtainStyledAttributes(
            attrs,  androidx.appcompat.R.styleable.Toolbar)

        if (a.hasValue(androidx.appcompat.R.styleable.Toolbar_titleTextColor)) {
            setTitleTextColor(a.getColorStateList( androidx.appcompat.R.styleable.Toolbar_titleTextColor)!!)
        }

        a.recycle()
    }

//    private val onNavigationClickListener: OnClickListener by lazy {
//        OnClickListener {
//            val activity = ContextHelper.findFragmentActivityContext(context)
//
//            try {
//                NavigationConfig.findNavController(activity)?.navigateUp()
//            } catch (e: RuntimeException) {
//                activity?.onBackPressed()
//            }
//        }
//    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

//        setNavigationOnClickListener(onNavigationClickListener)

//        try {
//            val activity = ContextHelper.findFragmentActivityContext(context)
//            val navController = NavigationConfig.findNavController(activity)
//            if (null != navController) {
//                NavigationUI.setupWithNavController(this, navController)
//            }
//        } catch (e: RuntimeException) {}
    }

    override fun setTitle(title: CharSequence?) {
        super.setTitle(null)

        ensureTitleTextView()

        if (tvTextTitle.parent == null) {
            addView(tvTextTitle)
        }

        tvTextTitle.text = title
    }

    override fun setTitleTextAppearance(context: Context?, resId: Int) {
        super.setTitleTextAppearance(context, resId)
        ensureTitleTextView()
        TextViewCompat.setTextAppearance(tvTextTitle, resId)
    }

    override fun setTitleTextColor(color: ColorStateList) {
        super.setTitleTextColor(color)
        ensureTitleTextView()
        tvTextTitle.setTextColor(color)
    }

    private fun ensureTitleTextView() {
        if (!::tvTextTitle.isInitialized) {
            tvTextTitle = TextView(context).apply {
                layoutParams = LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
                ).apply {
                        gravity = android.view.Gravity.CENTER
                    }
            }
        }
    }
}