package com.baltazar.testinglabapp

import com.baltazar.testinglabapp.provider.ApiProvider
import com.baltazar.testinglabapp.provider.DataBaseProvider
import com.baltazar.testinglabapp.provider.Repository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepositoryTest {

    private lateinit var mRepository: Repository
    private val mApiProvider: ApiProvider = mock()
    private val mDataBaseProvider: DataBaseProvider = mock()

    @BeforeEach
    fun init() {
        mRepository = Repository(mApiProvider, mDataBaseProvider)
    }

    @Test
    @DisplayName("User login. Should return an User")
    fun userLogin_shouldReturnAnUser() {
        val email = "user@email.com"
        val password = "123"
        val user = User().apply {
            firstName = "Baltazar"
            lastName = "Rodriguez"
            age = 25
        }
        whenever(mApiProvider.loginUser(email, password)).thenReturn(user)

        mRepository.userLogin(email, password)

        verify(mApiProvider).loginUser(email, password)
        verify(mDataBaseProvider).insertUser(user)
    }
}
