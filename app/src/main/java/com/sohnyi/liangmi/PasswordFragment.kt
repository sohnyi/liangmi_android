package com.sohnyi.liangmi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PasswordFragment : Fragment() {

    companion object {

        private const val ARG_PASSWORD_ID = "password_id"

        fun newInstance(passwordId: Int?): Fragment {
            val fragment = PasswordFragment()
            val args = Bundle()
            passwordId?.let {
                args.putInt(ARG_PASSWORD_ID, passwordId)
            }
            return fragment.apply {
                arguments = args
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_password, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}