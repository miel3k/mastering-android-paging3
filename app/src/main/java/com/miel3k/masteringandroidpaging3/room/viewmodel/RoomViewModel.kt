package com.miel3k.masteringandroidpaging3.room.viewmodel

import androidx.lifecycle.ViewModel
import com.miel3k.masteringandroidpaging3.data.user.UserDataSource
import com.miel3k.masteringandroidpaging3.di.UserRepositoryModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by jmielczarek on 18/09/2022
 */
@HiltViewModel
class RoomViewModel @Inject constructor(
    @UserRepositoryModule.RoomUserDataSource private val userRepository: UserDataSource
) : ViewModel() {

}