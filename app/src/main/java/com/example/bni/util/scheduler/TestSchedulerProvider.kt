package com.example.bni.util.scheduler

import io.reactivex.Scheduler

class TestSchedulerProvider(private val scheduler: Scheduler) : SchedulerProvider {
    override fun ui(): Scheduler = scheduler
    override fun io(): Scheduler = scheduler
}