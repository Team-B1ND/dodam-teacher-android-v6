package kr.hs.dgsw.smartschool.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.hs.dgsw.smartschool.local.dao.ClassroomDao
import kr.hs.dgsw.smartschool.local.dao.MealDao
import kr.hs.dgsw.smartschool.local.dao.MemberDao
import kr.hs.dgsw.smartschool.local.dao.ParentDao
import kr.hs.dgsw.smartschool.local.dao.PlaceDao
import kr.hs.dgsw.smartschool.local.dao.StudentDao
import kr.hs.dgsw.smartschool.local.dao.TeacherDao
import kr.hs.dgsw.smartschool.local.dao.TokenDao
import kr.hs.dgsw.smartschool.local.entity.classroom.ClassroomEntity
import kr.hs.dgsw.smartschool.local.entity.meal.MealEntity
import kr.hs.dgsw.smartschool.local.entity.member.MemberEntity
import kr.hs.dgsw.smartschool.local.entity.parent.ParentEntity
import kr.hs.dgsw.smartschool.local.entity.place.PlaceEntity
import kr.hs.dgsw.smartschool.local.entity.student.StudentEntity
import kr.hs.dgsw.smartschool.local.entity.teacher.TeacherEntity
import kr.hs.dgsw.smartschool.local.entity.token.TokenEntity

@Database(
    entities = [
        MealEntity::class, ClassroomEntity::class, MemberEntity::class, PlaceEntity::class,
        StudentEntity::class, TeacherEntity::class, ParentEntity::class, TokenEntity::class,
   ],
    version = 1,
    exportSchema = false
)

abstract class DodamTeacherDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
    abstract fun memberDao(): MemberDao
    abstract fun studentDao(): StudentDao
    abstract fun teacherDao(): TeacherDao
    abstract fun classroomDao(): ClassroomDao
    abstract fun parentDao(): ParentDao
    abstract fun placeDao(): PlaceDao
    abstract fun tokenDao(): TokenDao
}
