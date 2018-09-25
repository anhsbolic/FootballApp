package com.rioaska.studio.footballschedulematch.view.home.nextmatch

import android.content.Context
import com.rioaska.studio.footballschedulematch.api.TheSportApi
import com.rioaska.studio.footballschedulematch.model.ItemSchedule
import com.rioaska.studio.footballschedulematch.model.ScheduleResponse
import com.rioaska.studio.footballschedulematch.view.base.BasePresenterTest
import com.rioaska.studio.footballschedulematch.view.home.prevmatch.IMatchView
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.inOrder
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Rio on 12/09/18.
 * Email rio.aska35@gmail.com
 */
@RunWith(MockitoJUnitRunner::class)
class NextMatchPresenterTest : BasePresenterTest() {

    @Mock
    private
    lateinit var service: TheSportApi

    @Mock
    private
    lateinit var context: Context

    @Mock
    private
    lateinit var view: IMatchView

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, service, context)
    }

    @Test
    fun getMatchSuccess() {
        val list = mutableListOf<ItemSchedule>().apply {
            add(ItemSchedule())
        }
        val matchResponse = ScheduleResponse(list)
        `when`(service.getNextMatch("4406")).thenReturn(
                Observable.just(matchResponse)
        )

        val inOrder = inOrder(view)
        presenter.getMatch(true, "4406")
        inOrder.verify(view).showLoading(false)
        inOrder.verify(view).showMatchList(list)
    }

    @Test
    fun getMatchFailed() {
        val message = "Error get match"
        val error = Throwable(message)
        `when`(service.getNextMatch("4406")).thenReturn(
                Observable.error(error)
        )

        val inorder = inOrder(view)
        presenter.getMatch(false, "4406")
        inorder.verify(view).showLoading(false)
        inorder.verify(view).showMessage(ArgumentMatchers.anyString())
    }
}