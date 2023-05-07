package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.DataSourceRepository
import kr.hs.dgsw.smartschool.data.datasource.night_study.NightStudyRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.evening_study.NightStudy
import kr.hs.dgsw.smartschool.domain.repository.NightStudyRepository
import javax.inject.Inject

class NightStudyRepositoryImpl @Inject constructor(
    override val datasource: NightStudyRemoteDataSource
) : DataSourceRepository<NightStudyRemoteDataSource>, NightStudyRepository {

    override suspend fun getNightStudyByDate(date: String): List<NightStudy> =
        datasource.getNightStudyByDate(date)

    override suspend fun getNightStudy(): List<NightStudy> =
        datasource.getNightStudy()

    override suspend fun allowNightStudy(id: Int) =
        datasource.allowNightStudy(id)

    override suspend fun denyNightStudy(id: Int) =
        datasource.denyNightStudy(id)

    override suspend fun getPendingNightStudy(): List<NightStudy> =
        datasource.getPendingNightStudy()
}
