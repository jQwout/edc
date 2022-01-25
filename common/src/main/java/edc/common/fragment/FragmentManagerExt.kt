package edc.common.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.commit(
    fragment: Fragment,
    transition: Int? = FragmentTransaction.TRANSIT_FRAGMENT_OPEN,
    containerViewId: Int = android.R.id.content,
    tag: String? = null,
    runOnCommitBlock: Runnable? = null
) {
    beginTransaction()
        .apply {
            if (transition != null) {
                setTransition(transition)
            }

            add(containerViewId, fragment)

            if (tag != null) {
                addToBackStack(tag)
            }

            runOnCommitBlock?.let { runOnCommit(it) }
        }
        .commit()
}