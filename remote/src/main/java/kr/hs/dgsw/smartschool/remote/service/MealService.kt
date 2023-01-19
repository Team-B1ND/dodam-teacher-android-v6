package kr.hs.dgsw.smartschool.remote.service

import kr.hs.dgsw.smartschool.remote.response.Response
import kr.hs.dgsw.smartschool.remote.response.meal.MealResponse
import kr.hs.dgsw.smartschool.remote.url.DodamUrl
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {

    @GET(DodamUrl.Meal.SINGLE)
    suspend fun getMeal(
        @Query("day") day: Int,
        @Query("month") month: Int,
        @Query("year") year: Int,
    ): Response<MealResponse>

    @GET(DodamUrl.Meal.MONTH)
    suspend fun getMealOfMonth(
        @Query("month") month: Int,
        @Query("year") year: Int,
    ): Response<List<MealResponse>>

    @GET(DodamUrl.Meal.CALORIE)
    suspend fun getCalorieOfMeal(): Response<String?>

}