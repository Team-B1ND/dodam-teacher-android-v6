package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.datasource.night_study.NightStudyRemoteDataSource
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.request.night_study.NightStudyIdRequest
import kr.hs.dgsw.smartschool.remote.service.NightStudyService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall
import javax.inject.Inject

class NightStudyRemoteDataSourceImpl @Inject constructor(
    private val eveningStudyService: NightStudyService
) : NightStudyRemoteDataSource {

    override suspend fun getNightStudyByDate(date: String) = dodamApiCall {
        eveningStudyService.getNightStudyByDate(date).data.toModel()
    }

    override suspend fun getNightStudy() = dodamApiCall {
        eveningStudyService.getNightStudy().data.toModel()
    }

    override suspend fun allowNightStudy(id: Int) = dodamApiCall {
        eveningStudyService.allowNightStudy(id.toNightStudyIdRequest())
    }

    override suspend fun cancelNightStudy(id: Int) {
        eveningStudyService.cancelNightStudy(id.toNightStudyIdRequest())
    }

    private fun Int.toNightStudyIdRequest(): NightStudyIdRequest =
        NightStudyIdRequest(
            id = this
        )
}