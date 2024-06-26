package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.out.OutCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.out.OutRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import java.time.LocalDateTime
import javax.inject.Inject

class OutRepositoryImpl @Inject constructor(
    override val remote: OutRemoteDataSource,
    override val cache: OutCacheDataSource,
) : BaseRepository<OutRemoteDataSource, OutCacheDataSource>, OutRepository {
    override suspend fun getOutgoingByDate(date: String): List<Out> =
        remote.getOutgoingByDate(date)

    override suspend fun getOutSleepingByDate(date: String): List<Out> =
        remote.getOutSleepingByDate(date)

    override suspend fun getOutSleepingValid(): List<Out> =
        remote.getOutSleepingValidByDate()

    /* 날짜 변경 시 필수로 호출되는 함수 */
//    override suspend fun getOutsByDateRemote(date: String): Out =
//        remote.getOutsByDate(date).apply {
//            cache.deleteAllOut()
//            cache.insertOuts(this.outgoings + this.outsleepings)
//        }

    /* 필수로 getOutsByDateRemote 함수가 선행이 되어야함 */
//    override suspend fun getOutsByDateLocal(date: LocalDateTime): Out {
//        val outgoings = emptyList<OutItem>().toMutableList()
//        val outsleepings = emptyList<OutItem>().toMutableList()
//
//        cache.getAllOut().let { out ->
//            out.outgoings.map { outItem ->
//                if (!outItem.startOutDate.toLocalDate().isBefore(date.toLocalDate()))
//                    outgoings.add(outItem)
//            }
//
//            out.outsleepings.map { outItem ->
//                if (!outItem.startOutDate.toLocalDate().isBefore(date.toLocalDate()))
//                    outsleepings.add(outItem)
//            }
//        }
//
//        return Out(
//            outgoings = outgoings,
//            outsleepings = outsleepings
//        )
//    }

    override suspend fun getOutgoingById(id: Int): OutItem =
        cache.getOutById(id) ?: remote.getOutgoing(id)

    override suspend fun getOutsleepingById(id: Int): OutItem =
        cache.getOutById(id) ?: remote.getOutsleeping(id)

    override suspend fun getOutgoingsByDate(date: LocalDateTime): List<OutItem> {
        val outgoings = emptyList<OutItem>().toMutableList()

        cache.getOutgoings().let { outItems ->
            outItems.map {
                if (it.startOutDate.isAfter(date)) {
                    outgoings.add(it)
                }
            }
        }

        return outgoings
    }

    override suspend fun getOutsleepingsByDate(date: LocalDateTime): List<OutItem> {
        val outsleepings = emptyList<OutItem>().toMutableList()

        cache.getOutsleepings().let { outItems ->
            outItems.map {
                if (it.startOutDate.isAfter(date)) {
                    outsleepings.add(it)
                }
            }
        }

        return outsleepings
    }

    override suspend fun getOutByStudentId(id: Int): List<OutItem> =
        cache.getOutByStudentId(id)

    override suspend fun clearOutData() =
        cache.deleteAllOut()

    override suspend fun allowOutgoing(id: Int) =
        remote.allowOutgoing(id)

    override suspend fun deleteOutgoing(id: Int) =
        remote.deleteOutgoing(id).apply {
            cache.deleteOutById(id)
        }

    override suspend fun cancelAllowOutgoing(id: Int) =
        remote.cancelAllowOutgoing(id)

    override suspend fun denyOutgoing(ids: List<Int>) =
        remote.denyOutgoing(ids)

    override suspend fun deleteOutsleeping(id: Int) {
        remote.deleteOutsleeping(id).let {
            cache.deleteOutById(id)
        }
    }

    override suspend fun allowOutsleeping(id: Int) =
        remote.allowOutsleeping(id)

    override suspend fun cancelAllowOutsleeping(id: Int) =
        remote.cancelAllowOutsleeping(id)

    override suspend fun denyOutsleeping(ids: List<Int>) =
        remote.denyOutsleeping(ids)
}
