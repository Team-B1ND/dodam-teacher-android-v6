package kr.hs.dgsw.smartschool.remote.request.reject

import com.google.gson.annotations.SerializedName

data class RejectReasonRequest(
    @field:SerializedName("rejectReason") val rejectReason: String,
)
fun String.toNightStudyIdRequest(): RejectReasonRequest =
    RejectReasonRequest(
        rejectReason = this
    )