package dev.skymansandy.scratchcardlayoutexample.ui.fragment.aboutdev

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.aadevelopers.scratchcardview.databinding.FragmentAboutDevBinding

class AboutDevFragment : DialogFragment() {

    companion object {
        fun getInstance(): AboutDevFragment {
            val bundle = Bundle()
            val aboutDevFragment = AboutDevFragment()
            aboutDevFragment.arguments = bundle
            return aboutDevFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentAboutDevBinding.inflate(inflater).root
    }
}