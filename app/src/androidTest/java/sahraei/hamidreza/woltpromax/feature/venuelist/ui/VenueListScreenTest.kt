package sahraei.hamidreza.woltpromax.feature.venuelist.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsToggleable
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runAndroidComposeUiTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import sahraei.hamidreza.woltpromax.MainActivity
import sahraei.hamidreza.woltpromax.common.widget.LikeButtonTag
import sahraei.hamidreza.woltpromax.common.widget.ProgressBarTag
import java.util.concurrent.TimeUnit

@HiltAndroidTest
class VenueListScreenTest {

    @get:Rule
    var hiltTestRule = HiltAndroidRule(this)

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun shouldShowProgressBarFirst() = runAndroidComposeUiTest<MainActivity>{
        onNodeWithTag(ProgressBarTag).assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun shouldShowLikeButton() = runAndroidComposeUiTest<MainActivity>{
        waitUntil(
            timeoutMillis = TimeUnit.SECONDS.toMillis(10)
        ) {
            onAllNodesWithTag(VenueItemTestTag).fetchSemanticsNodes().isNotEmpty()
        }
        onAllNodesWithTag(LikeButtonTag).onFirst().assertIsToggleable()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun shouldLikeVenueByLikeClick() = runAndroidComposeUiTest<MainActivity>{
        waitUntil(
            timeoutMillis = TimeUnit.SECONDS.toMillis(10)
        ) {
            onAllNodesWithTag(VenueItemTestTag).fetchSemanticsNodes().isNotEmpty()
        }
        onAllNodesWithTag(LikeButtonTag).onFirst().assertIsOff()
        onAllNodesWithTag(LikeButtonTag).onFirst().performClick()
        onAllNodesWithTag(LikeButtonTag).onFirst().assertIsOn()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun shouldShowLoadingSnackbarAfter10Seconds() = runAndroidComposeUiTest<MainActivity>{
        waitUntil(
            timeoutMillis = TimeUnit.SECONDS.toMillis(10)
        ) {
            onAllNodesWithTag(VenueItemTestTag).fetchSemanticsNodes().isNotEmpty()
        }
        waitUntil(
            timeoutMillis = TimeUnit.SECONDS.toMillis(11)
        ) {
            onAllNodesWithTag(SnackbarTestTag).fetchSemanticsNodes().size == 1
        }
        onNodeWithTag(SnackbarTestTag).assertIsDisplayed()
    }
}