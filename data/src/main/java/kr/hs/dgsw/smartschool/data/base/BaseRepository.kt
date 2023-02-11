package kr.hs.dgsw.smartschool.data.base

interface BaseRepository<REMOTE, CACHE> {
    val remote: REMOTE
    val cache: CACHE
}
