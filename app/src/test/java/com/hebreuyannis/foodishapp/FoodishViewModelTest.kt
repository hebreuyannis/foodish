package com.hebreuyannis.foodishapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hebreuyannis.foodishapp.app.coroutine.DefaultDispatcherProvider
import com.hebreuyannis.foodishapp.app.coroutine.DispatcherProvider
import com.hebreuyannis.foodishapp.app.favorite.FavoriteFoodishRepository
import com.hebreuyannis.foodishapp.app.favorite.ShouldRegisterDecider
import com.hebreuyannis.foodishapp.app.foodish.FoodishViewModel
import com.hebreuyannis.foodishapp.app.https.FoodishDownloader
import com.hebreuyannis.foodishapp.app.https.model.FoodishImgage
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.lastValue
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class FoodishViewModelTest {

    @get:Rule
    @Suppress("unused")
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var favoriteDecider: ShouldRegisterDecider

    @Mock
    private lateinit var mockCommandObserver: Observer<FoodishViewModel.Command>

    @Captor
    private lateinit var commandCaptor: ArgumentCaptor<FoodishViewModel.Command>

    @Mock
    private lateinit var downloader: FoodishDownloader

    @Mock
    private lateinit var repository: FavoriteFoodishRepository

    private lateinit var viewModelTest: FoodishViewModel

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        viewModelTest = FoodishViewModel(DefaultDispatcherProvider(), downloader,repository,favoriteDecider)
        viewModelTest.command.observeForever(mockCommandObserver)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun after() {
        viewModelTest.command.removeObserver(mockCommandObserver)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun whenButtonFavoriteWasHitThenAddToRepository() = runBlocking<Unit> {
        val url = "http://example.com"
        whenever(favoriteDecider.shouldRegister(url)).doReturn(true)
        viewModelTest.favoriteFoodish(url)
        verify(repository).add(url)
    }

    @Test
    fun whenButtonFavoriteWasAlreadyHitThenRemoveToRepository() = runBlocking<Unit> {
        val url = "http://example.com"
        whenever(favoriteDecider.shouldRegister(url)).doReturn(false)
        viewModelTest.favoriteFoodish(url)
        verify(repository).delete(url)
    }

    @Test
    fun whenButtonFavoriteWasAlreadyHitThenUpdateCommandDelete() = runBlocking<Unit> {
        val url = "http://example.com"
        whenever(favoriteDecider.shouldRegister(url)).doReturn(false)
        viewModelTest.favoriteFoodish(url)
        verify(mockCommandObserver).onChanged(commandCaptor.capture())
        assertEquals(FoodishViewModel.Command.DeleteFavoriteFoodish,commandCaptor.lastValue)
    }

    @Test
    fun whenButtonFavoriteWasAlreadyHitThenUpdateCommandAdd() = runBlocking<Unit> {
        val url = "http://example.com"
        whenever(favoriteDecider.shouldRegister(url)).doReturn(true)
        viewModelTest.favoriteFoodish(url)
        verify(mockCommandObserver).onChanged(commandCaptor.capture())
        assertEquals(FoodishViewModel.Command.FavoriteFoodish,commandCaptor.lastValue)
    }

    /*@Test //unsucessful test
    fun whenButtonRefreshWasHitThenUpdateCommandSuccess() = runBlocking<Unit> {
        val imageUrl = FoodishImgage("http://example.com")
        whenever(downloader.downloadFoodish()).doReturn(imageUrl)
        whenever(favoriteDecider.shouldRegister(imageUrl.image)).doReturn(true)
        viewModelTest.refreshFoodish()
        verify(mockCommandObserver).onChanged(commandCaptor.capture())
        assertEquals(FoodishViewModel.Command.LoadFoodish(imageUrl.image),commandCaptor.lastValue)
    }*/

}