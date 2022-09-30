package com.itlifelang.jetnewremake.ui.interest

import androidx.annotation.StringRes
import com.itlifelang.jetnewremake.R

enum class InterestSection(@StringRes val titleId: Int) {
    Topics(R.string.interests_section_topics),
    People(R.string.interests_section_people),
    Publication(R.string.interests_section_publications)
}
