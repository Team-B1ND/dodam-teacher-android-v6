package kr.hs.dgsw.smartschool.local.datasource

import kr.hs.dgsw.smartschool.data.datasource.schedule.ScheduleCacheDataSource
import kr.hs.dgsw.smartschool.domain.model.schedule.Schedule
import kr.hs.dgsw.smartschool.local.dao.ScheduleDao
import kr.hs.dgsw.smartschool.local.mapper.toEntity
import kr.hs.dgsw.smartschool.local.mapper.toModel
import javax.inject.Inject

class ScheduleCacheDataSourceImpl @Inject constructor(
    private val scheduleDao: ScheduleDao,
) : ScheduleCacheDataSource {

    override suspend fun getSchedules(startYear: Int, startMonth: Int): List<Schedule> =
        scheduleDao.getSchedules(startYear, startMonth).toModel()

    override suspend fun getSchedule(id: Int): Schedule? =
        scheduleDao.getSchedule(id)?.toModel()

    override suspend fun deleteSchedules() =
        scheduleDao.deleteSchedules()

    override suspend fun insertSchedule(schedule: Schedule) =
        scheduleDao.insert(schedule.toEntity())

    override suspend fun insertSchedules(schedules: List<Schedule>) =
        scheduleDao.insert(schedules.toEntity())
}
