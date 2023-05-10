package kr.hs.dgsw.smartschool.data.datasource.night_study

import kr.hs.dgsw.smartschool.domain.model.night_study.NightStudy

interface NightStudyRemoteDataSource {

    suspend fun getNightStudyByDate(date: String): List<NightStudy>

    suspend fun getNightStudy(): List<NightStudy>

    suspend fun allowNightStudy(id: Int)

    suspend fun denyNightStudy(id: Int)

    suspend fun getPendingNightStudy(): List<NightStudy>
}
