package com.sohnyi.liangmi

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sohnyi.liangmi.entry.Password
import java.text.SimpleDateFormat
import java.util.*

class  PasswordFragment : Fragment() {

    companion object {

        private const val ARG_PASSWORD_ID = "password_id"
        private var password: Password? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val id = arguments?.getInt(ARG_PASSWORD_ID)
        id?.let {

        }

        setHasOptionsMenu(true)
        ViewModelProvider(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_password, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.new_password)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val updateTimeTv: TextView = view.findViewById(R.id.tv_update_time)

        updateTimeTv.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_password, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.give_me_a_password) {
            startActivity(PasswordGeneratorActivity.newIntent(requireActivity()))
        }
        return true
    }
}