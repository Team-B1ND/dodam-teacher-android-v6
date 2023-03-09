package kr.hs.dgsw.smartschool.remote.request.out

import com.google.gson.annotations.SerializedName

data class OutIdRequest(
    @field:SerializedName("outId") val outId: List<OutId>
) {
    data class OutId(
        val outId: Int,
    )
}
