package com.itlifelang.jetnewremake.ui.interest

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.itlifelang.jetnewremake.ui.interest.people.InterestPeoplePage
import com.itlifelang.jetnewremake.ui.interest.publication.InterestPublicationPage
import com.itlifelang.jetnewremake.ui.interest.topic.InterestTopicPage

@OptIn(ExperimentalPagerApi::class)
@Composable
fun InterestPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(),
    isExpandScreen: Boolean
) {
    HorizontalPager(
        count = InterestSection.values().size,
        modifier = modifier,
        state = pagerState
    ) {
        when (InterestSection.values()[it]) {
            InterestSection.Topics -> InterestTopicPage(isExpandScreen = isExpandScreen)
            InterestSection.People -> InterestPeoplePage(isExpandScreen = isExpandScreen)
            InterestSection.Publication -> InterestPublicationPage(isExpandScreen = isExpandScreen)
        }
    }
}
