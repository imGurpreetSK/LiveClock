package com.gurpreetsk.liveclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gurpreetsk.liveclock.databinding.ActivityMainBinding
import com.gurpreetsk.liveclock.ui.TimerAdapter
import com.gurpreetsk.liveclock.ui.utils.ItemExitEnterAnimator
import com.gurpreetsk.liveclock.utils.Clock
import com.gurpreetsk.liveclock.utils.DefaultSchedulersProvider
import com.gurpreetsk.liveclock.utils.LocalDateTimeClock
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import java.time.LocalDateTime
import kotlin.LazyThreadSafetyMode.NONE

class MainActivity : ComponentActivity() {

    private val binding by lazy(NONE) { ActivityMainBinding.inflate(layoutInflater) }

    private val timerAdapter by lazy(NONE) { TimerAdapter() }

    private val timeTicker by lazy(NONE) { TimeTicker(LocalDateTimeClock(), DefaultSchedulersProvider) }

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupTimerComponent()
    }

    override fun onStart() {
        super.onStart()
        timeTicker
            .observe()
            .distinctUntilChanged()
            .doOnNext { timerAdapter.submitList(it) }
            .subscribe()
            .addTo(compositeDisposable)
    }

    override fun onStop() {
        compositeDisposable.dispose()
        super.onStop()
    }

    private fun setupTimerComponent() {
        binding.timer.adapter = timerAdapter
        binding.timer.layoutManager = object : LinearLayoutManager(this, HORIZONTAL, false) {
            override fun canScrollHorizontally(): Boolean = false
            override fun canScrollVertically(): Boolean = false
        }
        binding.timer.itemAnimator = ItemExitEnterAnimator()
    }
}
