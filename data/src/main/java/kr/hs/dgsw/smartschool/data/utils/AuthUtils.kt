package kr.hs.dgsw.smartschool.data.utils

import java.security.MessageDigest

fun String.encryptSHA512(): String {
    val messageDigest =
        MessageDigest.getInstance("SHA-512")
    val encryptedPassword = StringBuilder()
    messageDigest.update(this.toByteArray())
    val buffer = messageDigest.digest()
    for (temp in buffer) {
        var sb =
            StringBuilder(Integer.toHexString(temp.toInt()))
        while (sb.length < 2) {
            sb.insert(0, "0")
        }
        sb = StringBuilder(sb.substring(sb.length - 2))
        encryptedPassword.append(sb)
    }
    return encryptedPassword.toString()
}
