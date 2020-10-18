package com.hebreuyannis.foodishapp

import com.hebreuyannis.foodishapp.app.favorite.FavoriteDecider
import com.hebreuyannis.foodishapp.app.favorite.FavoriteFoodishRepository
import com.hebreuyannis.foodishapp.app.favorite.ShouldRegisterDecider
import com.hebreuyannis.foodishapp.app.utils.DefaultDispatcherProvider
import com.hebreuyannis.foodishapp.model.FavoriteRepositoryMock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class FoodishTest {
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var favoriteDecider: ShouldRegisterDecider
    private lateinit var repository: FavoriteFoodishRepository

    @Before
    fun setup() {
        repository = Mockito.mock(FavoriteFoodishRepository::class.java)
        favoriteDecider = FavoriteDecider(repository, DefaultDispatcherProvider())
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Empty array return true`() = runBlocking {
        Mockito.`when`(repository.getList()).thenReturn(arrayListOf())
        val result =
            favoriteDecider.shouldRegister("https://foodish-api.herokuapp.com/images/burger/burger56.jpg")
        Assert.assertEquals(true, result)
    }

    @Test
    fun `should register return false`() = runBlocking {
        Mockito.`when`(repository.getList())
            .thenReturn(arrayListOf("https://foodish-api.herokuapp.com/images/burger/burger56.jpg"))
        val result =
            favoriteDecider.shouldRegister("https://foodish-api.herokuapp.com/images/burger/burger56.jpg")
        Assert.assertEquals(false, result)
    }

    @Test
    fun `add item on repository and should return 4`() {
        val newRepository = FavoriteRepositoryMock()
        newRepository.add("https://foodish-api.herokuapp.com/images/burger/burger59.jpg")
        Assert.assertEquals(4, newRepository.getList().size)
    }

    @Test
    fun `remove item on repository and should return 3`() {
        val newRepository = FavoriteRepositoryMock()
        val item = newRepository.get(newRepository.getList().size - 1)
        newRepository.delete(item)
        Assert.assertEquals(2, newRepository.getList().size)
    }
}