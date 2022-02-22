package com.example.kotlin_cardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.kotlin_cardgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textInputLayout.editText?.addTextChangedListener(listener)
    }

    val listener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                if (s.isEmpty()) {
                    binding.textInputLayout.error = "닉네임을 입력하세요."
                    binding.button.isEnabled = false
                }
                else if (s.length > 5) {
                    binding.textInputLayout.error = "닉네임은 5글자 아래로 작성하세요."
                    binding.button.isEnabled = false
                }
                else if (hasSpecialCharacter(s.toString())) {
                    binding.textInputLayout.error = "특수문자를 포함할 수 없습니다."
                    binding.button.isEnabled = false
                }
                else if (!hasAlphabet(s.toString()) && s.isNotEmpty()) {
                    binding.textInputLayout.error = "영문자를 최소 한 개 포함해야 합니다. "
                    binding.button.isEnabled = false
                }
                else {
                    binding.textInputLayout.error = null
                    binding.button.isEnabled = true
                }
            }
        }
    }

    // 특수문자 존재 여부를 확인하는 메서드
    fun hasSpecialCharacter(string: String): Boolean {
        for (i in string.indices) {
            if (!Character.isLetterOrDigit(string[i])) {
                return true
            }
        }
        return false
    }

    // 영문자 존재 여부를 확인하는 메서드
    fun hasAlphabet(string: String): Boolean {
        for (i in string.indices) {
            if (Character.isAlphabetic(string[i].code)) {
                return true
            }
        }
        return false
    }
}

