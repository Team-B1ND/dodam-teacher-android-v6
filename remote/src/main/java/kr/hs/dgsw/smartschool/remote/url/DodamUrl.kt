package kr.hs.dgsw.smartschool.remote.url

object DodamUrl {

    const val BASE_URL = "http://101.101.209.184:8080/api/"
    const val MEAL = "meal"

    object Meal {
        const val SINGLE = MEAL
        const val CALORIE = "$MEAL/calorie"
        const val MONTH = "$MEAL/month"
    }
}
