package com.aldhykohar.scrollmapinsidescrollview.utilities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.gms.maps.SupportMapFragment


/**
 * Created by aldhykohar on 6/3/2021.
 */
class WorkAroundMapFragment : SupportMapFragment() {
    private var mListener: OnTouchListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = super.onCreateView(layoutInflater, container, savedInstanceState)

        val frameLayout = TouchableWrapper(activity)

        frameLayout.setBackgroundColor(resources.getColor(android.R.color.transparent))

        (layout as ViewGroup?)!!.addView(
            frameLayout,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        return layout
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnTouchListener) {
            mListener = context as OnTouchListener
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement FragmentAListener"
            )
        }
    }

    interface OnTouchListener {
        fun onTouch()
    }

    inner class TouchableWrapper(context: Context?) : FrameLayout(context!!) {
        override fun dispatchTouchEvent(event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> mListener?.onTouch()
            }
            return super.dispatchTouchEvent(event)
        }
    }
}