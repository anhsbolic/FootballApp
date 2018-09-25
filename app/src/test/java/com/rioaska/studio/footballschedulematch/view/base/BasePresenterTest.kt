package com.rioaska.studio.footballschedulematch.view.base

import org.junit.ClassRule

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
open class BasePresenterTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxSchedulerRule()
    }

}