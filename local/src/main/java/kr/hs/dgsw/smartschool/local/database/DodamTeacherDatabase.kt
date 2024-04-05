package kr.hs.dgsw.smartschool.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kr.hs.dgsw.smartschool.local.dao.AccountDao
import kr.hs.dgsw.smartschool.local.dao.BannerDao
import kr.hs.dgsw.smartschool.local.dao.CalorieDao
import kr.hs.dgsw.smartschool.local.dao.MealDao
import kr.hs.dgsw.smartschool.local.dao.MemberDao
import kr.hs.dgsw.smartschool.local.dao.OutDao
import kr.hs.dgsw.smartschool.local.dao.ParentDao
import kr.hs.dgsw.smartschool.local.dao.PlaceDao
import kr.hs.dgsw.smartschool.local.dao.ScheduleDao
import kr.hs.dgsw.smartschool.local.dao.StudentDao
import kr.hs.dgsw.smartschool.local.dao.TeacherDao
import kr.hs.dgsw.smartschool.local.dao.TimeTableDao
import kr.hs.dgsw.smartschool.local.dao.TokenDao
import kr.hs.dgsw.smartschool.local.entity.account.AccountEntity
import kr.hs.dgsw.smartschool.local.entity.banner.BannerEntity
import kr.hs.dgsw.smartschool.local.entity.calorie.CalorieEntity
import kr.hs.dgsw.smartschool.local.entity.meal.MealEntity
import kr.hs.dgsw.smartschool.local.entity.member.MemberEntity
import kr.hs.dgsw.smartschool.local.entity.out.OutEntity
import kr.hs.dgsw.smartschool.local.entity.parent.ParentEntity
import kr.hs.dgsw.smartschool.local.entity.place.PlaceEntity
import kr.hs.dgsw.smartschool.local.entity.schedule.ScheduleEntity
import kr.hs.dgsw.smartschool.local.entity.student.StudentEntity
import kr.hs.dgsw.smartschool.local.entity.teacher.TeacherEntity
import kr.hs.dgsw.smartschool.local.entity.timetable.TimeTableEntity
import kr.hs.dgsw.smartschool.local.entity.token.TokenEntity
import kr.hs.dgsw.smartschool.local.table.DodamTable

@Database(
    entities = [
        MealEntity::class, MemberEntity::class, PlaceEntity::class,
        StudentEntity::class, TeacherEntity::class, ParentEntity::class, TokenEntity::class,
        AccountEntity::class, OutEntity::class, BannerEntity::class, ScheduleEntity::class,
        CalorieEntity::class, TimeTableEntity::class
    ],
    version = 15,
    exportSchema = false
)

abstract class DodamTeacherDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
    abstract fun memberDao(): MemberDao
    abstract fun studentDao(): StudentDao
    abstract fun teacherDao(): TeacherDao
    abstract fun parentDao(): ParentDao
    abstract fun placeDao(): PlaceDao
    abstract fun tokenDao(): TokenDao
    abstract fun accountDao(): AccountDao
    abstract fun outDao(): OutDao
    abstract fun bannerDao(): BannerDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun calorieDao(): CalorieDao
    abstract fun timeTableDao(): TimeTableDao
}

val MIGRATION_1_TO_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.run {
            execSQL("CREATE TABLE ${DodamTable.CLASSROOM} (id INTEGER not null, placeId INTEGER not null, grade INTEGER not null, room INTEGER not null, PRIMARY KEY (id))")
            execSQL("CREATE TABLE ${DodamTable.MEMBER} (id TEXT not null, joinDate TEXT, role TEXT not null, name TEXT not null, profileImage TEXT, email TEXT not null, status TEXT not null, PRIMARY KEY (id))")
            execSQL("CREATE TABLE ${DodamTable.PLACE} (name INTEGER not null, placeTypeId INTEGER not null, id INTEGER not null, placeTypeName TEXT not null, primary key (id))")
            execSQL("CREATE TABLE ${DodamTable.STUDENT} (studentId INTEGER not null, classroomId INTEGER not null, number INTEGER not null, phone TEXT not null, memberId TEXT not null, memberName TEXT not null, primary key (studentId))")
            execSQL("CREATE TABLE ${DodamTable.TEACHER} (tel TEXT not null, teacherId INTEGER not null, position TEXT not null, phone TEXT not null, memberId TEXT not null, primary key (teacherId))")
            execSQL("CREATE TABLE ${DodamTable.PARENT} (studentId INTEGER not null, phone TEXT not null, id INTEGER not null, primary key (id))")
            execSQL("CREATE TABLE ${DodamTable.TOKEN} (idx INTEGER not null, token TEXT not null, refreshToken TEXT not null, primary key (idx))")
            execSQL("CREATE TABLE ${DodamTable.ACCOUNT} (idx INTEGER not null, id TEXT not null, pw TEXT not null, primary key (idx))")
        }
    }
}

val MIGRATION_11_TO_12: Migration = object : Migration(11, 12) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.run {
            execSQL("CREATE TABLE ${DodamTable.CLASSROOM} (id INTEGER not null, placeId INTEGER not null, grade INTEGER not null, room INTEGER not null, PRIMARY KEY (id))")
            execSQL("CREATE TABLE ${DodamTable.MEMBER} (id TEXT not null, joinDate TEXT, role TEXT not null, name TEXT not null, profileImage TEXT, email TEXT not null, status TEXT not null, PRIMARY KEY (id))")
            execSQL("CREATE TABLE ${DodamTable.PLACE} (name INTEGER not null, placeTypeId INTEGER not null, id INTEGER not null, placeTypeName TEXT not null, primary key (id))")
            execSQL("CREATE TABLE ${DodamTable.STUDENT} (studentId INTEGER not null, classroomId INTEGER not null, number INTEGER not null, phone TEXT not null, memberId TEXT not null, memberName TEXT not null, primary key (studentId))")
            execSQL("CREATE TABLE ${DodamTable.TEACHER} (tel TEXT not null, teacherId INTEGER not null, position TEXT not null, phone TEXT not null, memberId TEXT not null, primary key (teacherId))")
            execSQL("CREATE TABLE ${DodamTable.PARENT} (studentId INTEGER not null, phone TEXT not null, id INTEGER not null, primary key (id))")
            execSQL("CREATE TABLE ${DodamTable.TOKEN} (idx INTEGER not null, token TEXT not null, refreshToken TEXT not null, primary key (idx))")
            execSQL("CREATE TABLE ${DodamTable.ACCOUNT} (idx INTEGER not null, id TEXT not null, pw TEXT not null, primary key (idx))")
        }
    }
}