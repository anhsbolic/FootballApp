package com.rioaska.studio.footballschedulematch.view.home.teamlist

import android.content.Context
import com.rioaska.studio.footballschedulematch.api.TheSportApi
import com.rioaska.studio.footballschedulematch.model.Team
import com.rioaska.studio.footballschedulematch.model.TeamResponse
import com.rioaska.studio.footballschedulematch.view.base.BasePresenterTest
import com.rioaska.studio.footballschedulematch.view.home.teams.ITeamsView
import com.rioaska.studio.footballschedulematch.view.home.teams.TeamListPresenter
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
class TeamListPresenterTest : BasePresenterTest() {

    @Mock
    private
    lateinit var service: TheSportApi

    @Mock
    private
    lateinit var context: Context

    @Mock
    private
    lateinit var view: ITeamsView

    private lateinit var presenter: TeamListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamListPresenter(view, service, context)
    }

    @Test
    fun getMatchSuccess() {
        val list = mutableListOf<Team>().apply {
            add(Team())
        }
        val matchResponse = TeamResponse(list)
        `when`(service.getTeamAllLeague("4406")).thenReturn(
                Observable.just(matchResponse)
        )

        val inOrder = inOrder(view)
        presenter.getTeams(true, "4406")
        inOrder.verify(view).showLoading(false)
        inOrder.verify(view).showTeamList(list)
    }

    @Test
    fun getMatchFailed() {
        val message = "Error get match"
        val error = Throwable(message)
        `when`(service.getTeamAllLeague("4406")).thenReturn(
                Observable.error(error)
        )

        val inorder = inOrder(view)
        presenter.getTeams(false, "4406")
        inorder.verify(view).showLoading(false)
        inorder.verify(view).showMessage(ArgumentMatchers.anyString())
    }
}