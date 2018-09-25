package com.rioaska.studio.footballschedulematch.api

import com.rioaska.studio.footballschedulematch.BuildConfig
import com.rioaska.studio.footballschedulematch.model.*
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 *
 * Created by Rio on 31/08/18.
 * Email rio.aska35@gmail.com
 *
 */
interface TheSportApi {

    companion object {
        val instance: TheSportApi by lazy {
            val okHttpClientBuilder = OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BASIC
                okHttpClientBuilder.addInterceptor(logging)
            }
            val retrofit = Retrofit.Builder()
                    .client(okHttpClientBuilder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.BASE_URL)
                    .build()
            retrofit.create(TheSportApi::class.java)
        }

    }

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php")
    fun getNextMatch(@Query("id") idTeam: String): Observable<ScheduleResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php")
    fun getPrevMatch(@Query("id") idTeam: String): Observable<ScheduleResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_teams.php")
    fun getTeamAllLeague(@Query("id") idTeam: String): Observable<TeamResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookup_all_players.php")
    fun getPlayerTeam(@Query("id") idTeam: String): Observable<PlayerResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php")
    fun getDetailTeam(@Query("id") idTeam: String): Observable<TeamResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/searchteams.php?s=Soccer")
    fun searchTeam(@Query("t") idTeam: String): Observable<TeamResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/searchevents.php")
    fun searchMatch(@Query("e") idTeam: String): Observable<SearchMatchResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php")
    fun getMatchDetail(@Query("id") idMatch: String): Observable<MatchDetailResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/search_all_leagues.php?s=Soccer")
    fun getAllLeague(): Observable<LeagueResponse>

}