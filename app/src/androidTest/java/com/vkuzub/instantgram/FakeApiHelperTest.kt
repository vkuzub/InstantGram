package com.vkuzub.instantgram

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.vkuzub.instantgram.data.network.FakeApiHelperImpl
import com.vkuzub.instantgram.data.network.models.response.Post
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FakeApiHelperTest {

    @Test
    fun testApiHelperReturnsNonNull() {
        val fakeApiHelper = FakeApiHelperImpl(InstrumentationRegistry.getTargetContext())

        val result = fakeApiHelper.loadAllPosts().blockingGet()
        Assert.assertNotNull(result)
    }

    @Test
    fun testApiHelperReturnsList() {
        val fakeApiHelper = FakeApiHelperImpl(InstrumentationRegistry.getTargetContext())

        val result = fakeApiHelper.loadAllPosts().blockingGet()
        Assert.assertTrue(result is List<Post>)
    }
}