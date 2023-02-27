package kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeSideEffect
import kr.hs.dgsw.smartschool.dodamdodam_teacher.features.main.home.mvi.HomeState
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {

    override val container: Container<HomeState, HomeSideEffect> = container(HomeState())

}
