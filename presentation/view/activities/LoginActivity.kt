package rs.raf.vezbe11.presentation.view.activities

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.User
import rs.raf.vezbe11.data.repositories.UserRepository
import rs.raf.vezbe11.presentation.view.states.AddUserState
import rs.raf.vezbe11.presentation.view.states.UsersState
import timber.log.Timber


class LoginActivity(
        private val userRepository: UserRepository
    ) : AppCompatActivity() {

    private val subscriptions = CompositeDisposable()

    val usersState: MutableLiveData<UsersState> = MutableLiveData()
    val addUserDone: MutableLiveData<AddUserState> = MutableLiveData()

    private var usernameEditText: EditText? = null
        private var passwordEditText: EditText? = null
        private var loginBtn: Button? = null
    private var registerBtn: Button? = null
    private var savedPassword: String? = null
        private var messageWritten = false


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)
            init()
        }

        fun init() {
            initView()
            initListeneres()
        }

        private fun initView() {
            usernameEditText = findViewById<EditText>(R.id.editTextUsername)
            passwordEditText = findViewById<EditText>(R.id.editTextPassword)
            loginBtn = findViewById<Button>(R.id.buttonLogin)
//            try {
//                val inputStream = resources.openRawResource(R.raw.saved_password)
//                val bufferedReader = BufferedReader(InputStreamReader(inputStream))
//                savedPassword = bufferedReader.readLine()
//                bufferedReader.close()
//                inputStream.close()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
        }

        private fun initListeneres() {
            loginBtn!!.setOnClickListener(View.OnClickListener { v: View? ->
                val username = usernameEditText!!.text.toString().trim { it <= ' ' }
                val password = passwordEditText!!.text.toString().trim { it <= ' ' }

                // check if all fields are filled
                if (TextUtils.isEmpty(username)) {
                    usernameEditText!!.error = "This field is required"
                    return@OnClickListener
                }
                if (TextUtils.isEmpty(password)) {
                    passwordEditText!!.error = "This field is required"
                    return@OnClickListener
                }
                if (password != savedPassword) {
                    passwordEditText!!.error = "Incorrect password"
                    return@OnClickListener
                }
                val sharedPreferences =
                    getSharedPreferences(packageName, MODE_PRIVATE)
                sharedPreferences
                    .edit()
                    .putString(PREF_MESSAGE_KEY, username)
                    .apply()
                messageWritten = true
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.apply()
                val editor2 = sharedPreferences.edit()
                editor2.apply()
                val result: Int
                result = if (messageWritten) RESULT_OK else RESULT_CANCELED
                setResult(result)
                finish()
            })

            registerBtn!!.setOnClickListener(View.OnClickListener { v: View? ->
                val username = usernameEditText!!.text.toString().trim { it <= ' ' }
                val password = passwordEditText!!.text.toString().trim { it <= ' ' }



                val sharedPreferences =
                    getSharedPreferences(packageName, MODE_PRIVATE)
                sharedPreferences
                    .edit()
                    .putString(PREF_MESSAGE_KEY, username)
                    .apply()
                messageWritten = true
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.apply()
                val editor2 = sharedPreferences.edit()
                editor2.apply()
                val result: Int
                result = if (messageWritten) RESULT_OK else RESULT_CANCELED
                setResult(result)
                finish()
            })
        }

        companion object {
            const val PREF_MESSAGE_KEY = "prefMessageKey"
        }

    fun getAllUsers() {
        val subscription = userRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    usersState.value = UsersState.Success(it)
                },
                {
                    usersState.value = UsersState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    fun addUser(user: User) {
        val subscription = userRepository
            .insert(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addUserDone.value = AddUserState.Success
                },
                {
                    addUserDone.value = AddUserState.Error("Error happened while adding user")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    }