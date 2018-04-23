package com.vkuzub.instantgram

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.vkuzub.instantgram.data.db.DbHelperImpl
import com.vkuzub.instantgram.data.network.models.response.Post
import io.realm.Realm
import org.junit.After
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DbHelperTest {

    companion object {
        @BeforeClass
        fun setUp() {
            Realm.init(InstrumentationRegistry.getTargetContext())
        }
    }

    @After
    fun tearDown() {
        Realm.deleteRealm(Realm.getDefaultConfiguration())
    }

    @Test
    fun testDbHelperSavePostsNormalList() {
        val dbHelperImpl = DbHelperImpl()
        val simplePost = Post("92f5010c-abd6-4f19-ab14-ab3bd2d486c0")
        dbHelperImpl.savePosts(listOf(simplePost))

        val posts = dbHelperImpl.loadPosts()
        val postsAmount = posts?.size ?: 0
        val expected = 1
        Assert.assertEquals(expected, postsAmount)
    }

    @Test
    fun testDbHelperSavePostsEmptyList() {
        val dbHelperImpl = DbHelperImpl()
        dbHelperImpl.savePosts(listOf())

        val posts = dbHelperImpl.loadPosts()
        val postsAmount = posts?.size ?: 0
        val expected = 0
        Assert.assertEquals(expected, postsAmount)
    }
}