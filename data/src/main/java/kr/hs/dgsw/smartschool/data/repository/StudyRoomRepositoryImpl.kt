package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.student.StudentCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.studyroom.StudyRoomRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomList
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class StudyRoomRepositoryImpl @Inject constructor(
    override val remote: StudyRoomRemoteDataSource,
    override val cache : StudentCacheDataSource
) : BaseRepository<StudyRoomRemoteDataSource, Any>,StudyRoomRepository {

    val year: Int = LocalDate.now().year
    val month : Int = LocalDate.now().monthValue
    val day : Int = LocalDate.now().dayOfMonth
    override suspend fun getAllSheet()
    : StudyRoomList
    {
        val list = remote.getAllSheet(
            year , month , day
        ).studyRoomList!!.distinct()
        val otherStudents : MutableList<Student> = cache.getStudents().distinct().toMutableList()

        list.forEach { otherStudents.remove(it.student) }

        return StudyRoomList (
            list,
            otherStudents
        )
    }

    override suspend fun getSheetByTime(startTime: String, endTime: String): StudyRoomList {
        val list = remote.getAllSheet(year, month, day).studyRoomList!!.distinct().filter {
            it.timeTable.startTime == startTime && it.timeTable.endTime == endTime
        }
        val otherStudents : MutableList<Student> = cache.getStudents().distinct().toMutableList()

        list.forEach { otherStudents.remove(it.student) }

        return StudyRoomList(
            list,
            otherStudents
        )
    }

    override suspend fun checkStudyRoom(id: Int, isChecked: Boolean) {
        when(isChecked){
            true -> remote.unCheckStudyRoom(id)
            false -> remote.checkStudyRoom(id)
        }
    }

    override suspend fun ctrlStudyRoom(studentId: Int, studyRoomList: StudyRoomRequest) {
        remote.ctrlStudyRoom(studentId, studyRoomList)
    }
}