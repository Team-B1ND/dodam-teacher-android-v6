package kr.hs.dgsw.smartschool.remote.datasource

import android.util.Log
import kr.hs.dgsw.smartschool.data.datasource.night_study.NightStudyRemoteDataSource
import kr.hs.dgsw.smartschool.remote.mapper.toModel
import kr.hs.dgsw.smartschool.remote.request.night_study.NightStudyIdRequest
import kr.hs.dgsw.smartschool.remote.service.NightStudyService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall
import javax.inject.Inject

class NightStudyRemoteDataSourceImpl @Inject constructor(
    private val nightStudyService: NightStudyService
) : NightStudyRemoteDataSource {

//    override suspend fun getNightStudyByDate(date: String) = dodamApiCall {
//        nightStudyService.getNightStudyByDate(date).data.toModel()
//    }
//
    override suspend fun getNightStudy() = dodamApiCall {
        nightStudyService.getNightStudy().data.toModel()
    }

    override suspend fun allowNightStudy(id: Int) = dodamApiCall {
        nightStudyService.allowNightStudy(id)
    }

    override suspend fun denyNightStudy(id: Int) = dodamApiCall {
        nightStudyService.denyNightStudy(id, "null".toNightStudyIdRequest())
    }

    override suspend fun getPendingNightStudy() = dodamApiCall {
        nightStudyService.getPendingNightStudy().data.toModel()
    }

    private fun String.toNightStudyIdRequest(): NightStudyIdRequest =
        NightStudyIdRequest(
            rejectReason = this
        )
}
