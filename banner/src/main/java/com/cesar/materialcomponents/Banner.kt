package com.cesar.materialcomponents

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.coordinatorlayout.widget.CoordinatorLayout
import kotlinx.android.synthetic.main.material_banner.view.*

/**
 * TODO: document your custom view class.
 */
class Banner : CoordinatorLayout {

    private var _contentText: String? = "Please fill me in" // TODO: use a default from R.string...
    private var _leftButtonText: String? = "Dismiss" // TODO: use a default from R.string...
    private var _rightButtonText: String? = "Right Button" // TODO: use a default from R.string...

//    <attr name="contentText" format="string"/>
//    <attr name="leftButtonText" format="string"/>
//    <attr name="rightButtonText" format="string"/>

    /**
     * The text to draw
     */
    var contentText: String?
        get() = _contentText
        set(value) {
            _contentText = value
            contentTextView.text = value
        }

    var leftButtonText: String?
        get() = _leftButtonText
        set(value) {
            _leftButtonText = value
            leftButton.text = value
        }

    var rightButtonText: String?
        get() = _rightButtonText
        set(value) {
            _rightButtonText = value
            rightButton.text = value
        }


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        View.inflate(context, R.layout.material_banner, this)

        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.Banner, defStyle, 0
        )

        contentText = typedArray.getString(
            R.styleable.Banner_contentText
        )

        leftButtonText = typedArray.getString(
            R.styleable.Banner_leftButtonText
        )

        rightButtonText = typedArray.getString(
            R.styleable.Banner_rightButtonText
        )

        typedArray.recycle()

    }

    fun dismiss() = this.collapse()
    fun show() = this.expand()

    fun setLeftButtonAction(action: () -> Unit) = leftButton.setOnClickListener { action() }
    fun setRightButtonAction(action: () -> Unit) = rightButton.setOnClickListener { action() }


    private fun View.expand() {
        this@expand.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val targetHeight = this@expand.measuredHeight

        this@expand.layoutParams.height = 0
        this@expand.visibility = View.VISIBLE
        val animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                this@expand.layoutParams.height = if (interpolatedTime == 1f)
                    ViewGroup.LayoutParams.WRAP_CONTENT
                else
                    (targetHeight * interpolatedTime).toInt()
                this@expand.requestLayout()
            }

            override fun willChangeBounds(): Boolean = true
        }

        animation.duration = (targetHeight / this@expand.context.resources.displayMetrics.density).toInt().toLong()
        this@expand.startAnimation(animation)
    }

    private fun View.collapse() {
        val initialHeight = this.measuredHeight

        val animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    this@collapse.visibility = View.GONE
                } else {
                    this@collapse.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    this@collapse.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean = true
        }

        animation.duration = (initialHeight / this.context.resources.displayMetrics.density).toInt().toLong()
        this.startAnimation(animation)
    }

}
