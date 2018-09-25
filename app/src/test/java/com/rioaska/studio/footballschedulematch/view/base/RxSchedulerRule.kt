package com.rioaska.studio.footballschedulematch.view.base

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.functions.Function
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.Callable

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class RxSchedulerRule : TestRule {

    private val immediate: Function<Scheduler, Scheduler> = Function { Schedulers.trampoline() }

    private val mRxAndroidSchedulersHook: Function<Callable<Scheduler>, Scheduler> =
            Function { Schedulers.trampoline() }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.reset()
                RxJavaPlugins.setIoSchedulerHandler(immediate)
                RxJavaPlugins.setComputationSchedulerHandler(immediate)
                RxJavaPlugins.setNewThreadSchedulerHandler(immediate)
                RxAndroidPlugins.reset()
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(mRxAndroidSchedulersHook)

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}
