package kr.hs.dgsw.smartschool.local.datasource

import javax.inject.Inject
import kr.hs.dgsw.smartschool.data.datasource.out.OutCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.local.dao.OutDao
import kr.hs.dgsw.smartschool.local.mapper.toEntity
import kr.hs.dgsw.smartschool.local.mapper.toModel

class OutCacheDataSourceImpl @Inject constructor(
    private val outDao: OutDao,
) : OutCacheDataSource {

    override suspend fun getAllOut(): Out {

        val outgoings = emptyList<OutItem>().toMutableList()
        val outsleepings = emptyList<OutItem>().toMutableList()

        outDao.getAllOut().map {
            if (it.endOutDate == it.startOutDate)
                outgoings.add(it.toModel())
            else
                outsleepings.add(it.toModel())
        }

        return Out(
            outgoings = outgoings,
            outsleepings = outsleepings
        )
    }

    override suspend fun getOutById(id: Int): OutItem? =
        outDao.getOutById(id)?.toModel()

    override suspend fun getOutByStatus(status: String): List<OutItem> =
        outDao.getOutByStatus(status).map { it.toModel() }

    override suspend fun getOutByStudentId(studentId: Int): List<OutItem> =
        outDao.getOutByStudentId(studentId).map { it.toModel() }

    override suspend fun getOutgoings(): List<OutItem> =
        outDao.getOutgoing().map { it.toModel() }

    override suspend fun getOutsleepings(): List<OutItem> =
        outDao.getOutsleeping().map { it.toModel() }

    override suspend fun getAllowedOut(): List<OutItem> =
        outDao.getAllowedOut().map { it.toModel() }

    override suspend fun getNotAllowedOut(): List<OutItem> =
        outDao.getNotAllowedOut().map { it.toModel() }

    override suspend fun getAllowedOutgoing(): List<OutItem> =
        outDao.getAllowedOutgoing().map { it.toModel() }

    override suspend fun getNotAllowedOutgoing(): List<OutItem> =
        outDao.getNotAllowedOutgoing().map { it.toModel() }

    override suspend fun getAllowedOutsleeping(): List<OutItem> =
        outDao.getAllowedOutsleeping().map { it.toModel() }

    override suspend fun getNotAllowedOutsleeping(): List<OutItem> =
        outDao.getNotAllowedOutsleeping().map { it.toModel() }

    override suspend fun deleteAllOut() =
        outDao.deleteAllOut()

    override suspend fun deleteOutById(id: Int) =
        outDao.deleteOutById(id)

    override suspend fun insertOut(outItem: OutItem) =
        outDao.insert(outItem.toEntity())

    override suspend fun insertOuts(outItems: List<OutItem>) =
        outDao.insert(outItems.map { it.toEntity() })
}
