package ir.alirezanazari.vimeoapi

import ir.alirezanazari.vimeoapi.data.net.*
import ir.alirezanazari.vimeoapi.internal.Constants
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith
import org.hamcrest.CoreMatchers.`is` as Is

/**
 * open vpn to test
 */
@RunWith(JUnit4ClassRunner::class)
class TestNetworkManager {

    private val SEARCH_KEY = "art"
    private val STATIC_URI = "78562245"
    private lateinit var api: Api
    private lateinit var net: NetworkDataManager

    @Before
    fun init() {
        api = ApiConfig.invoke(TokenInterceptor())
        net = NetworkDataManagerImpl(api)
    }

    @Test
    fun `test search result`(): Unit = runBlocking {
        val resp = net.getSearchVideo(SEARCH_KEY, 1, Constants.Net.DIRECTION_ASC)
        assertThat(resp.isNullOrEmpty(), Is(false))
        assertThat(resp?.size, Is(not(0)))
    }

    @Test
    fun `test get video with specific uri`(): Unit = runBlocking {
        val resp = net.getVideo(STATIC_URI)
        assertThat(resp?.uri?.endsWith(STATIC_URI), Is(true))
    }

    @Test
    fun `test get mp4 url`(): Unit = runBlocking {
        val resp = net.getVideoMP4File(String.format(Constants.Net.PLAYER_BASE_URL, STATIC_URI))
        assertThat(resp?.size, Is(not(0)))
    }
}