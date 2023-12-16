package com.gurpreetsk.liveclock.ui.utils

import androidx.recyclerview.widget.RecyclerView
import com.gurpreetsk.liveclock.ui.utils.internal.BaseItemAnimator

/**
 * Repurposed from https://github.com/wasabeef/recyclerview-animators/blob/master/animators/src/main/java/jp/wasabeef/recyclerview/animators/FadeInUpAnimator.kt.
 */
class ItemExitEnterAnimator : BaseItemAnimator() {

    override fun animateRemoveImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().apply {
            translationY(holder.itemView.height * -0.25f)
            alpha(0.3f)
            duration = removeDuration
            setListener(DefaultRemoveAnimatorListener(holder))
            startDelay = getRemoveDelay(holder)
        }.start()
    }

    override fun preAnimateAddImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.translationY = holder.itemView.height * .25f
        holder.itemView.alpha = 0f
    }

    override fun animateAddImpl(holder: RecyclerView.ViewHolder) {
        holder.itemView.animate().apply {
            translationY(0f)
            alpha(1f)
            duration = addDuration
            interpolator = interpolator
            setListener(DefaultAddAnimatorListener(holder))
            startDelay = getAddDelay(holder)
        }.start()
    }
}
