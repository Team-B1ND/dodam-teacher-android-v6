package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.evening_study.NightStudy

interface NightStudyRepository {

    suspend fun getNightStudyByDate(date: String): List<NightStudy>

    suspend fun getNightStudy(): List<NightStudy>

    suspend fun allowNightStudy(id: Int)

    suspend fun denyNightStudy(id: Int)

    suspend fun getPendingNightStudy(): List<NightStudy>
}
