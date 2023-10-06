package rs.raf.vezbe11.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import rs.raf.vezbe11.R
import rs.raf.vezbe11.databinding.ActivityMainBinding
import rs.raf.vezbe11.presentation.view.adapters.MainPagerAdapter
import rs.raf.vezbe11.presentation.viewmodel.SplashViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

//    private var splashViewModel: SplashViewModel? = null
//
//    var sharedPreferencesActivityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
//        ActivityResultContracts.StartActivityForResult(),
//        ActivityResultCallback<ActivityResult> { result: ActivityResult ->
//            if (result.resultCode == RESULT_OK) {
//                // There are no request codes
//                // Neka vrednost je bila upisana na PreferenceActivity-u, proveravamo koja
//                val sharedPreferences =
//                    getSharedPreferences(packageName, MODE_PRIVATE)
//                var message =
//                    sharedPreferences.getString(LoginActivity.PREF_MESSAGE_KEY, "")
//                if (message == null) message = "Error"
//                Toast.makeText(this, "You are loggedi in$message", Toast.LENGTH_LONG).show()
//            } else {
//                Toast.makeText(this, "You are not logged in", Toast.LENGTH_SHORT).show()
//            }
//        })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
//        // Handle the splash screen transition.
//        val splashScreen: SplashScreen = SplashScreen.installSplashScreen(this)
//        splashScreen.setKeepOnScreenCondition {
//            try {
//                Thread.sleep(2000)
//            } catch (e: InterruptedException) {
//                throw RuntimeException(e)
//            }
//            false
//        }

//        MainActivity.recyclerViewModel = ViewModelProvider(this).get(RecyclerViewModel::class.java)

//        val sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
//        val message = sharedPreferences.getString(LoginActivity.PREF_MESSAGE_KEY, null)
//
//        if (message == null) {
//            // Nothing was written, start PreferenceActivity
//            val intent = Intent(this, LoginActivity::class.java)
//            sharedPreferencesActivityResultLauncher.launch(intent)
//        } else {
//            setContentView(R.layout.activity_main)
//            init()
//        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        binding.viewPager.adapter =
            MainPagerAdapter(
                supportFragmentManager,
                this
            )
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}
