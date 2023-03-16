package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.base.BaseRepository
import kr.hs.dgsw.smartschool.data.datasource.student.StudentCacheDataSource
import kr.hs.dgsw.smartschool.data.datasource.studyroom.StudyRoomRemoteDataSource
import kr.hs.dgsw.smartschool.data.datasource.timetable.TimeTableRemoteDataSource
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoomRequest
import kr.hs.dgsw.smartschool.domain.repository.StudyRoomRepository
import java.time.LocalDate
import javax.inject.Inject

class StudyRoomRepositoryImpl @Inject constructor(
    override val remote: StudyRoomRemoteDataSource,
    override val cache : StudentCacheDataSource
) : BaseRepository<StudyRoomRemoteDataSource, Any>,StudyRoomRepository {

    val year: Int = LocalDate.now().year
    val month : Int = LocalDate.now().monthValue
    val day : Int = LocalDate.now().dayOfMonth
    override suspend fun getAllSheet()
    : List<StudyRoom>
    {
        val list = remote.getAllSheet(
            year , month , day
        ).studyRoomList ?: emptyList()

        return list
    }

    override suspend fun getSheetByTime(startTime: String, endTime: String):  List<StudyRoom> {
        val list = remote.getAllSheet(year, month, day).studyRoomList ?: emptyList()
            val newList = list.filter {
            it.timeTable.startTime == startTime && it.timeTable.endTime == endTime
        }
        val otherStudents : MutableList<Student> = cache.getStudents().toMutableList()

        newList.forEach { otherStudents.remove(it.student) }

        return newList
    }

    override suspend fun getSheetByUserId(studentId: Int):  List<StudyRoom> {
        val list = remote.getAllSheet(year, month, day).studyRoomList ?: emptyList()
        val newList = list.filter {
            it.student.id == studentId
        }
        return newList
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