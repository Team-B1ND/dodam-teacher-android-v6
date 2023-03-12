package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.schedule.ScheduleCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.schedule.ScheduleRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.schedule.Schedule
import kr.hs.dgsw.smartschool.domain.repository.ScheduleRepository
import java.time.LocalDate
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    override val remote: ScheduleRemoteDataSource,
    override val cache: ScheduleCacheDataSource
) : BaseRepository<ScheduleRemoteDataSource, ScheduleCacheDataSource>, ScheduleRepository {

    override suspend fun getSchedules(startDate: LocalDate, endDate: LocalDate): List<Schedule> =
        cache.getSchedules(startDate.year, startDate.monthValue).ifEmpty {
            remote.getSchedules(startDate.toString(), endDate.toString()).apply {
                cache.insertSchedules(this)
            }
        }

    override suspend fun getSchedule(id: Int): Schedule? =
        cache.getSchedule(id)
}
