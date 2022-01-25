package edc.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import edc.app.databinding.MainActivityBinding
import edc.exchange.view.ExchangeFragment
import edc.valutes.view.ValuteFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val valutesFragment: ValuteFragment by lazy(LazyThreadSafetyMode.NONE) {
        ValuteFragment()
    }

    private val exchangeFragment: ExchangeFragment by lazy(LazyThreadSafetyMode.NONE) {
        ExchangeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemReselectedListener { }
        binding.bottomNavigation.setOnItemSelectedListener {
            val f = when (it.itemId) {
                R.id.exchangeFragment -> exchangeFragment
                R.id.valuteFragment -> valutesFragment
                else -> throw IllegalStateException()
            }

            supportFragmentManager.commit {
                replace(R.id.mainMenuHost, f)
            }

            true
        }
        binding.bottomNavigation.selectedItemId = R.id.exchangeFragment
    }
}