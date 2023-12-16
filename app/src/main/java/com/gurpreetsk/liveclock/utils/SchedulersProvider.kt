package com.gurpreetsk.liveclock.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

interface SchedulersProvider {

    fun io(): Scheduler

    fun ui(): Scheduler
}

object DefaultSchedulersProvider : SchedulersProvider {

    override fun io(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}
