package com.example.imagecrawler

import android.view.View
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UtilTest {

    @Mock
    private lateinit var randomView: View

    @Test
    fun test_01() {
        val serverId = "12345"
        val id = "123"
        val secret = "abc"
        val expectedUrl = "https://live.staticflickr.com/12345/123_abc.jpg"
        val testUrl = convertUrl(serverId, id, secret)
        Truth.assertThat(testUrl).isEqualTo(expectedUrl)
    }

    @Test
    fun test_02() {
        randomView.show()
        Truth.assertThat(randomView.visibility).isEqualTo(View.VISIBLE)
    }

    @Test
    fun test_03() {
        randomView.hide()
        Truth.assertThat(randomView.visibility).isEqualTo(View.GONE)
    }
}