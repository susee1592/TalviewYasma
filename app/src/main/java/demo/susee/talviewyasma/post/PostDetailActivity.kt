package demo.susee.talviewyasma.post

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import demo.susee.talviewyasma.R
import demo.susee.talviewyasma.Result

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        val post = intent.getParcelableExtra<Result.Post>("post")

    }
}
