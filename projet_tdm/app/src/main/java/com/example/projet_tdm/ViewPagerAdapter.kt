package com.example.projet_tdm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.projet_tdm.entity.Onboarding

class ViewPagerAdapter(private var context: Context,private var onboardingList: List<Onboarding>) : PagerAdapter() {

    var pos : Int = 0
    override fun getCount(): Int {
        return onboardingList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object`as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view= LayoutInflater.from(context).inflate(R.layout.onboarding_screen_layaout,null)
        val imageView :ImageView
        val title :TextView
        val desc:TextView

        pos = position

        imageView =view.findViewById(R.id.imageView)
        title =view.findViewById(R.id.titre)
        desc =view.findViewById(R.id.desc)

        imageView.setImageResource(onboardingList[position].imgUrl)
        title.text=onboardingList[position].title
        desc.text=onboardingList[position].desc

        container.addView(view)
        return view
    }

}