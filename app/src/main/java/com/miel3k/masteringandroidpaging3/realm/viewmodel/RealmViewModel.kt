package com.miel3k.masteringandroidpaging3.realm.viewmodel

import androidx.lifecycle.ViewModel
import com.miel3k.masteringandroidpaging3.data.users.UsersDataSource
import com.miel3k.masteringandroidpaging3.di.UsersRepositoryModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by jmielczarek on 18/09/2022
 */
@HiltViewModel
class RealmViewModel @Inject constructor(
    @UsersRepositoryModule.RealmUsersDataSource private val usersRepository: UsersDataSource
) : ViewModel() {

}