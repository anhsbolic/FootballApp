package com.rioaska.studio.footballschedulematch.view.detail

import com.rioaska.studio.footballschedulematch.api.TheSportApi
import com.rioaska.studio.footballschedulematch.model.Match
import com.rioaska.studio.footballschedulematch.model.MatchDetailResponse
import com.rioaska.studio.footballschedulematch.model.Team
import com.rioaska.studio.footballschedulematch.model.TeamResponse
import com.rioaska.studio.footballschedulematch.view.base.BasePresenterTest
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Rio on 12/09/18.
 * Email rio.aska35@gmail.com
 */
@RunWith(MockitoJUnitRunner::class)
class DetailMatchPresenterTest : BasePresenterTest() {

    @Mock
    private
    lateinit var service: TheSportApi

    @Mock
    private
    lateinit var view: IDetailMatchView

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view, service)
    }

    @Test
    fun getDetailMatchSuccess() {
        val id = "576504"
        val list = mutableListOf<Match>().apply {
            add(Match())
        }
        val matchResponse = MatchDetailResponse(list)
        Mockito.`when`(service.getMatchDetail(id)).thenReturn(
                Observable.just(matchResponse)
        )

        val inOrder = Mockito.inOrder(view)
        presenter.getDetailMatch(id)
        inOrder.verify(view).showLoading(false)
        inOrder.verify(view).showDetailMatch(list[0])
    }

    @Test
    fun getDetailMatchFailed() {
        val message = "Error get match"
        val id = "576504"
        val error = Throwable(message)
        Mockito.`when`(service.getMatchDetail(id)).thenReturn(
                Observable.error(error)
        )

        val inorder = Mockito.inOrder(view)
        presenter.getDetailMatch(id)
        inorder.verify(view).showLoading(false)
        inorder.verify(view).showMessage(ArgumentMatchers.anyString())
        inorder.verify(view).showErrorLayout(true)
    }

    @Test
    fun getBadgeHomeSuccess() {
        val id = "133637"
        val home = 1
        val list = mutableListOf<Team>().apply {
            add(Team())
        }
        val teamResponse = TeamResponse(list)
        Mockito.`when`(service.getDetailTeam(id)).thenReturn(
                Observable.just(teamResponse)
        )

        val inOrder = Mockito.inOrder(view)
        presenter.getBadge(id, home)
        inOrder.verify(view).showBadgeImageHome(list[0].strTeamBadge ?: "")
    }

}