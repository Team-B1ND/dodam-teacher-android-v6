package kr.hs.dgsw.smartschool.domain.model.member.student

import org.json.JSONObject

data class Student(
    val id: Int,
    val name: String,
    val grade: Int,
    val room: Int,
    val number: Int,
) {
    companion object {
        fun fromJsonString(jsonString: String): Student {
            val jsonObject = JSONObject(jsonString)
            val id = jsonObject.getInt("id")
            val name = jsonObject.getString("name")
            val grade = jsonObject.getInt("grade")
            val room = jsonObject.getInt("room")
            val number = jsonObject.getInt("number")
            return Student(id, name, grade, room, number)
        }
    }

    fun toJsonString(): String {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("name", name)
        jsonObject.put("grade", grade)
        jsonObject.put("room", room)
        jsonObject.put("number", number)
        return jsonObject.toString()
    }
}
