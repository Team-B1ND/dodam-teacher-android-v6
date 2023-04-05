package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.timetable.TimeTableCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.timetable.TimeTableRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.timetable.TimeTable
import kr.hs.dgsw.smartschool.domain.repository.TimeTableRepository
import javax.inject.Inject

class TimeTableRepositoryImpl @Inject constructor(
    override val remote: TimeTableRemoteDataSource,
    override val cache: TimeTableCacheDataSource,
) : BaseRepository<TimeTableRemoteDataSource, TimeTableCacheDataSource>, TimeTableRepository {

    override suspend fun setTimeTables(): List<TimeTable> =
        remote.getTimeTables().apply {
            cache.deleteTimeTables()
            cache.insertTimeTables(this)
        }

    override suspend fun getTimeTables(): List<TimeTable> =
        cache.getTimeTables().ifEmpty {
            cache.getTimeTables().apply {
                cache.deleteTimeTables()
                cache.insertTimeTables(this)
            }
        }
}
