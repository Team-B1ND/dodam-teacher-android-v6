package kr.hs.dgsw.smartschool.data.datasource.implemenation

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.cache.MealCache
import kr.hs.dgsw.smartschool.data.remote.MealRemote

class MealDataSourceImpl(
    override val remote: MealRemote,
    override val cache: MealCache,
) : BaseDataSource<MealRemote, MealCache> {

}