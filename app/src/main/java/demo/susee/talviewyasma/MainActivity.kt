package demo.susee.talviewyasma

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import demo.susee.talviewyasma.album.AlbumFragment
import demo.susee.talviewyasma.post.PostFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout.setupWithViewPager(viewPager, true)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        tabLayout.getTabAt(0)!!.text = "Posts"
        tabLayout.getTabAt(1)!!.text = "Albums"
    }

    class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return if (position == 0) {
                PostFragment()
            } else
                AlbumFragment()
        }

        override fun getCount(): Int {
            return 2
        }
    }
}
