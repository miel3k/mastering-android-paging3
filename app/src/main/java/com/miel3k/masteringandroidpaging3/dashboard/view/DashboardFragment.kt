package com.miel3k.masteringandroidpaging3.dashboard.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.miel3k.masteringandroidpaging3.R
import com.miel3k.masteringandroidpaging3.databinding.FragmentDashboardBinding
import com.miel3k.masteringandroidpaging3.realm.view.RealmFragment
import com.miel3k.masteringandroidpaging3.room.view.RoomFragment
import com.miel3k.masteringandroidpaging3.utils.lifecycleBinding

/**
 * Created by miel3k on 18/09/2022
 */
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private val binding by lifecycleBinding { FragmentDashboardBinding.bind(requireView()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRoomButton()
        initRealmButton()
    }

    private fun initRoomButton() {
        binding.mcvRoom.setOnClickListener { navigate(RoomFragment()) }
    }

    private fun initRealmButton() {
        binding.mcvRealm.setOnClickListener { navigate(RealmFragment()) }
    }

    private fun navigate(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}