package ru.netology.nmedia.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editText = intent?.getStringExtra("editText")
        if (!editText.isNullOrBlank())
            binding.content.setText(editText)
        binding.ok.setOnClickListener {
            val text = binding.content.text.toString()
            if (text.isNullOrBlank())
            {
                setResult(RESULT_CANCELED)
            } else {
                setResult(RESULT_OK, Intent().apply { putExtra(Intent.EXTRA_TEXT, text) })
            }
            finish()
        }
    }
}

object NewPostContract: ActivityResultContract<String?, String?>() {
    override fun createIntent(context: Context, input: String?): Intent = Intent(context, NewPostActivity::class.java).putExtra("editText", input)
    override fun parseResult(resultCode: Int, intent: Intent?): String? = intent?.getStringExtra(Intent.EXTRA_TEXT)
}