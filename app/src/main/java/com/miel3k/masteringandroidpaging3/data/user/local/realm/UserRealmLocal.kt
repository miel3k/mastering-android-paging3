package com.miel3k.masteringandroidpaging3.data.user.local.realm

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagingSource
import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.toLiveData
import com.miel3k.masteringandroidpaging3.data.user.local.UserLocalDataSource
import com.miel3k.masteringandroidpaging3.data.user.model.User
import io.realm.Realm
import io.realm.kotlin.delete
import io.realm.kotlin.where
import javax.inject.Inject

/**
 * Created by jmielczarek on 18/09/2022
 */
class UserRealmLocal @Inject constructor(private val realm: Realm) : UserLocalDataSource {

    override fun observeUsers(): LiveData<Result<List<User>>> {
        val userResults = realm.where<User>().findAll()
        return Transformations.map(userResults.toLiveData()) { realmResults ->
            realmResults?.let {
                realm.copyFromRealm(it)?.let { data -> Result.Success(data) }
            } ?: Result.Error(Exception())
        }
    }

    override suspend fun saveUsers(users: List<User>) {
        realm.executeTransaction { it.insertOrUpdate(users) }
    }

    override fun deleteUsers() {
        realm.executeTransaction { realm.delete<User>() }
    }

    override fun getUsersPagingSource(): PagingSource<Int, User> {
        val userResults = realm.where<User>().findAll()
        return UserRealmPagingSource(userResults)
    }
}