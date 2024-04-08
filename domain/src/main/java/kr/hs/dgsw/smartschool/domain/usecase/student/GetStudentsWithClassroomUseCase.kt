package kr.hs.dgsw.smartschool.domain.usecase.student

import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.student.Student
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import javax.inject.Inject

class GetStudentsWithClassroomUseCase @Inject constructor(
    private val studentRepository: StudentRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        studentRepository.getStudents().map {
            Student(
                id = it.id,
                member = it.member,
                number = it.number,
                phone = it.phone,
                classroom = Classroom(
                    1
                )
            )
        }
    }
}
