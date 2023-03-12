package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.mvi.ItmapSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.itmap.mvi.ItmapState
import kr.hs.dgsw.smartschool.domain.usecase.itmap.GetCompaniesUseCase
import kr.hs.dgsw.smartschool.domain.usecase.itmap.SetCompaniesUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class ItmapViewModel @Inject constructor(
    private val setCompaniesUseCase: SetCompaniesUseCase,
    private val getCompaniesUseCase: GetCompaniesUseCase,
) : ContainerHost<ItmapState, ItmapSideEffect>, ViewModel() {

    override val container: Container<ItmapState, ItmapSideEffect> = container(ItmapState())


}
