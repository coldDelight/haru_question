package com.colddelight.haru_question.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colddelight.data.local.Prefs
import com.colddelight.domain.model.DomainQuestion
import com.colddelight.domain.use_case.QuestionUseCase
import com.colddelight.haru_question.core.util.HaruState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val UseCase: QuestionUseCase,
    private val prefs: Prefs
):ViewModel(){
    data class QuestionState(
        val questionData: DomainQuestion = DomainQuestion("","",""),
        val state: HaruState = HaruState.READY,
        val error: String = ""
    )
    private val _state = MutableStateFlow(QuestionState())
    val state: StateFlow<QuestionState> = _state.asStateFlow()

    init{
        prefs.isChecked=false
        prefs.questionId=1
        prefs.lastDate="NO_DATE"
        setHomeTitle()
        viewModelScope.launch(Dispatchers.IO)  {

        }
    }
    private fun setHomeTitle(){
        val dateNow = LocalDate.now()
        if(dateNow.equals(prefs.lastDate)){
            _state.value = QuestionState(
                state= HaruState.WAIT
            )
        }else if(prefs.isChecked){
            setQuestion()
        }else{
            _state.value = QuestionState(
                state= HaruState.READY
            )
        }
    }
    fun checkQuestion(){
        prefs.isChecked=true
        setQuestion()
    }

    private fun setQuestion(){
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = QuestionState(
                state = HaruState.SHOW,
                questionData = UseCase.getQuestion(prefs.questionId)
            )
        }
    }

}