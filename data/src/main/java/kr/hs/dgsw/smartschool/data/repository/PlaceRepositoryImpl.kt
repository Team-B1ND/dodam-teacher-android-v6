package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.place.PlaceRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.student.StudentCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.studyroom.StudyRoomRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.domain.repository.PlaceRepository
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    override val remote: PlaceRemoteDataSource,
) : BaseRepository<PlaceRemoteDataSource, Any>, PlaceRepository {

    override val cache: Any
        get() = TODO("Not yet implemented")

    override suspend fun getPlaces(): List<Place> {
        return remote.getPlaces()
    }
}