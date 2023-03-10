package kr.hs.dgsw.smartschool.remote.datasource

import kr.hs.dgsw.smartschool.data.datasource.out.OutRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutType
import kr.hs.dgsw.smartschool.remote.mapper.toOut
import kr.hs.dgsw.smartschool.remote.mapper.toOutItem
import kr.hs.dgsw.smartschool.remote.request.out.OutIdRequest
import kr.hs.dgsw.smartschool.remote.service.OutService
import kr.hs.dgsw.smartschool.remote.utils.dodamApiCall
import javax.inject.Inject

class OutRemoteDataSourceImpl @Inject constructor(
    private val outService: OutService,
) : OutRemoteDataSource {

    override suspend fun getOutsByDate(date: String): Out = dodamApiCall {
        outService.getOutsByDate(date).data.toOut()
    }

    override suspend fun getOutgoing(id: Int): OutItem = dodamApiCall {
        outService.getOutgoing(id).data.toOutItem(OutType.OUTGOING)
    }

    override suspend fun deleteOutgoing(id: Int) = dodamApiCall {
        outService.deleteOutgoing(id).data
    }

    override suspend fun allowOutgoing(ids: List<Int>) = dodamApiCall {
        outService.allowOutgoing(ids.toOutIdRequest()).data
    }

    override suspend fun cancelAllowOutgoing(ids: List<Int>) = dodamApiCall {
        outService.cancelAllowOutgoing(ids.toOutIdRequest()).data
    }

    override suspend fun denyOutgoing(ids: List<Int>) = dodamApiCall {
        outService.denyOutgoing(ids.toOutIdRequest()).data
    }

    override suspend fun getOutsleeping(id: Int): OutItem = dodamApiCall {
        outService.getOutsleeping(id).data.toOutItem(OutType.OUTSLEEPING)
    }

    override suspend fun deleteOutsleeping(id: Int) = dodamApiCall {
        outService.deleteOutsleeping(id).data
    }

    override suspend fun allowOutsleeping(ids: List<Int>) = dodamApiCall {
        outService.allowOutsleeping(ids.toOutIdRequest()).data
    }

    override suspend fun cancelAllowOutsleeping(ids: List<Int>) = dodamApiCall {
        outService.cancelAllowOutsleeping(ids.toOutIdRequest()).data
    }

    override suspend fun denyOutsleeping(ids: List<Int>) = dodamApiCall {
        outService.denyOutsleeping(ids.toOutIdRequest()).data
    }

    private fun List<Int>.toOutIdRequest(): OutIdRequest =
        OutIdRequest(
            outId = this
        )
}
