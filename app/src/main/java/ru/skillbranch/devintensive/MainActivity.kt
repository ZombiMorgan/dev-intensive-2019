package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener, TextView.OnEditorActionListener {
    lateinit var benderImage : ImageView
    lateinit var textTxt : TextView
    lateinit var messageEt : EditText
    lateinit var sendBtn : ImageView

    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        benderImage = findViewById(R.id.iv_bender)
        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        makeSendOnActionDone(messageEt)
        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this)



    }

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity","onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity","onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)
        Log.d("M_MainActivity","onSaveInstanceState ${benderObj.status.name} ${benderObj.question.name}")
    }

//    override fun onClick(p0: View?) {
//        if(p0?.id == R.id.iv_send) {
//            val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
//            messageEt.setText("")
//            val (r,g,b) = color
//            benderImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
//            textTxt.text = phrase
//        }
//    }
    override fun onClick(v: View?) {
    if (v?.id == R.id.iv_send)
        if (isAnswerValid())
            sendAnswer()
        else makeErrorMessage()
}

    //    Валидация
//    Question.NAME -> "Имя должно начинаться с заглавной буквы"
//    Question.PROFESSION -> "Профессия должна начинаться со строчной буквы"
//    Question.MATERIAL -> "Материал не должен содержать цифр"
//    Question.BDAY -> "Год моего рождения должен содержать только цифры"
//    Question.SERIAL -> "Серийный номер содержит только цифры, и их 7"
//    Question.IDLE -> //игнорировать валидацию

    private fun makeErrorMessage() {
        val errorMessage = when (benderObj.question) {
            Bender.Question.NAME -> "Имя должно начинаться с заглавной буквы"
            Bender.Question.PROFESSION -> "Профессия должна начинаться со строчной буквы"
            Bender.Question.MATERIAL -> "Материал не должен содержать цифр"
            Bender.Question.BDAY -> "Год моего рождения должен содержать только цифры"
            Bender.Question.SERIAL -> "Серийный номер содержит только цифры, и их 7"
            else -> "На этом все, вопросов больше нет"
        }
        textTxt.text = errorMessage + "\n" + benderObj.question.question
        messageEt.setText("")
    }

    private fun isAnswerValid(): Boolean {
        return benderObj.question.validate(messageEt.text.toString())
    }

    private fun sendAnswer() {
        val (phase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
        messageEt.setText("")
        val (r, g, b) = color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        textTxt.text = phase
    }

    private fun makeSendOnActionDone(editText: EditText) {
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT)
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) sendBtn.performClick()
            false
        }
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean =
        if (v?.id == R.id.et_message && actionId == EditorInfo.IME_ACTION_DONE) {
            sendAnswer()
            hideKeyboard()
            true
        } else false
}
