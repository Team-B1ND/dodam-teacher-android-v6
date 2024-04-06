package kr.hs.dgsw.smartschool.domain.model.member.teacher

import kr.hs.dgsw.smartschool.domain.model.member.Member
import org.json.JSONObject

data class Teacher(
    val id: Int,
    val position: String,
    val tel: String,
) {
    companion object {
        fun fromJsonString(jsonString: String): Teacher {
            val jsonObject = JSONObject(jsonString)
            val id = jsonObject.getInt("id")
            val position = jsonObject.getString("position")
            val tel = jsonObject.getString("tel")
            return Teacher(id, position, tel)
        }
    }

    fun toJsonString(): String {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("position", position)
        jsonObject.put("tel", tel)
        return jsonObject.toString()
    }
}
