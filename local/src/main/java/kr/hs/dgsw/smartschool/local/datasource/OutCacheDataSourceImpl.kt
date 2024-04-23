package kr.hs.dgsw.smartschool.local.datasource

import kr.hs.dgsw.smartschool.data.datasource.out.OutCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutType
import kr.hs.dgsw.smartschool.local.dao.OutDao
import kr.hs.dgsw.smartschool.local.mapper.toEntity
import kr.hs.dgsw.smartschool.local.mapper.toModel
import javax.inject.Inject

class OutCacheDataSourceImpl @Inject constructor(
    private val outDao: OutDao,
) : OutCacheDataSource {

    override suspend fun getOutgoings(): List<Out> =
        outDao.getOutgoing().map { it.toModel() }

    override suspend fun getOutsleepings(): List<Out> =
        outDao.getOutsleeping().map { it.toModel() }

    override suspend fun getNotAllowedOut(): List<Out> =
        outDao.getNotAllowedOut().map { it.toModel() }

    override suspend fun getAllowedOutgoing(): List<Out> =
        outDao.getAllowedOutgoing().map { it.toModel() }

    override suspend fun getNotAllowedOutgoing(): List<Out> =
        outDao.getNotAllowedOutgoing().map { it.toModel() }

    override suspend fun getAllowedOutsleeping(): List<Out> =
        outDao.getAllowedOutsleeping().map { it.toModel() }

    override suspend fun getNotAllowedOutsleeping(): List<Out> =
        outDao.getNotAllowedOutsleeping().map { it.toModel() }

    override suspend fun deleteAllOut() =
        outDao.deleteAllOut()

    override suspend fun deleteTypeAllOut(type: OutType) {
        outDao.deleteAllTypeOut(type.name)
    }

    override suspend fun deleteOutById(id: Int) =
        outDao.deleteOutById(id)

    override suspend fun insertOuts(type: OutType, outItems: List<Out>) =
        outDao.insert(outItems.map { it.toEntity(type) })
}
