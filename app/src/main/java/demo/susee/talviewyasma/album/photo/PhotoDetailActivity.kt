package demo.susee.talviewyasma.album.photo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import com.squareup.picasso.Picasso
import demo.susee.talviewyasma.R
import kotlinx.android.synthetic.main.activity_photo_detail.*


class PhotoDetailActivity : AppCompatActivity() {
    private var revealX: Int = 0
    private var revealY: Int = 0
    private var image_url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)


        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
            intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
            intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)
        ) {
            root_layout.visibility = View.INVISIBLE

            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0)
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0)
            image_url = intent.getStringExtra("EXTRA_IMAGE_URL")


            val viewTreeObserver = root_layout.viewTreeObserver
            if (viewTreeObserver.isAlive) {
                viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        revealActivity(revealX, revealY)
                        root_layout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
            }
        } else {
            root_layout.visibility = View.VISIBLE
        }

        Picasso.get().load(image_url).into(iv_fullscreen)
    }

    protected fun revealActivity(x: Int, y: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val finalRadius = (Math.max(root_layout.width, root_layout.height) * 1.1).toFloat()

            // create the animator for this view (the start radius is zero)
            val circularReveal = ViewAnimationUtils.createCircularReveal(root_layout, x, y, 0f, finalRadius)
            circularReveal.duration = 400
            circularReveal.interpolator = AccelerateInterpolator()

            // make the view visible and start the animation
            root_layout.visibility = View.VISIBLE
            circularReveal.start()
        } else {
            finish()
        }
    }

    protected fun unRevealActivity() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            finish()
        } else {
            val finalRadius = (Math.max(root_layout.width, root_layout.height) * 1.1).toFloat()
            val circularReveal = ViewAnimationUtils.createCircularReveal(
                root_layout, revealX, revealY, finalRadius, 0f
            )

            circularReveal.duration = 400
            circularReveal.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    root_layout.visibility = View.INVISIBLE
                    finish()
                }
            })


            circularReveal.start()
        }
    }

    companion object {
        val EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X"
        val EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y"
    }
}

