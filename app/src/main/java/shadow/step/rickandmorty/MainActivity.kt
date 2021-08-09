package shadow.step.rickandmorty

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import shadow.step.rickandmorty.data.Character
import shadow.step.rickandmorty.data.CharacterList
import shadow.step.rickandmorty.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_chars, R.id.navigation_locations, R.id.navigation_episodes
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        addTestCharacters()
    }

    private fun addTestCharacters(){
        for (n in 1..10) {
            val randomNumber = Random()
            CharacterList.characterListItems.add(
                Character(
                    R.drawable.ic_launcher_background,
                    "Name$n",
                    "Race$n",
                    "Status$n",
                    "Gender$n")
            )
        }
    }
}