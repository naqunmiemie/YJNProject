package com.yjn.yjnproject.ui.base

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModelMVI<ViewEvent:BaseViewEvent,ViewState:BaseViewState> : BaseViewModel() {
    /**
     * 界面的初始状态
     */
    private val initialState by lazy {
        providerInitialState()
    }

    /**
     * 包含UI所有的状态
     * StateFlow和协程结合使用可以达到LiveData的效果
     */
    private val _viewState = MutableStateFlow(initialState)
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    /**
     * 接收用户意图
     * SharedFlow特点
     * 1.的数据会以流的形式发送，不会丢失，新事件不会覆盖旧事件
     * 2.数据不是粘性的，消费一次就不会再次出现
     * 3.无法接收到 collect 之前发送的事件
     */
    private val _viewEvent = MutableSharedFlow<ViewEvent>()
    val viewEvent: SharedFlow<ViewEvent> = _viewEvent.asSharedFlow()

    abstract fun providerInitialState(): ViewState

    abstract fun handleEvent(viewEvent: ViewEvent)

    init{
        subscribeEvents()
    }

    /**
     * 订阅事件
     */
    private fun subscribeEvents() {
        viewModelScope.launch {
            viewEvent.collect {
                handleEvent(it)
            }
        }
    }

    /**
     * 发送意图
     */
    fun sendEvent(viewEvent: ViewEvent) {
        viewModelScope.launch {
            _viewEvent.emit(viewEvent)
        }
    }


    /**
     * 发送状态
     */
    fun setState(viewState: ViewState) {
        viewModelScope.launch {
            _viewState.emit(viewState)
        }
    }

}