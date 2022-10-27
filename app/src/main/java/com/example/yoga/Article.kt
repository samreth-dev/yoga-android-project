package com.example.yoga

import android.content.Context

data class Article(var title: String, var source: String, var link: String = "") {
    companion object {
        fun getArticles(context: Context): ArrayList<Article>{
            return arrayListOf(
                Article(context.getString(R.string.artTitle1), "Harvard Medical School", "https://www.health.harvard.edu/staying-healthy/yoga-benefits-beyond-the-mat"),
                Article(context.getString(R.string.artTitle2), "Greater Good Magazine", "https://greatergood.berkeley.edu/article/item/why_yoga_is_good_for_your_body_and_brain_according_to_science"),
                Article(context.getString(R.string.artTitle3), "NY Times", "https://greatergood.berkeley.edu/article/item/why_yoga_is_good_for_your_body_and_brain_according_to_science"),
                Article(context.getString(R.string.artTitle4), "Healthline", "https://www.healthline.com/nutrition/13-benefits-of-yoga"),
                Article(context.getString(R.string.artTitle5), "Yoga International", "https://www.healthline.com/nutrition/13-benefits-of-yoga"), 
                Article(context.getString(R.string.artTitle6), "Yoga International", "https://yogainternational.com/article/view/relieve-neck-tension-with-this-short-therapeutic-practice"),
                Article(context.getString(R.string.artTitle7), "Yoga International", "https://yogainternational.com/article/view/sweet-coconut-and-turmeric-milk"),
                Article(context.getString(R.string.artTitle8), "Yoga International", "https://yogainternational.com/article/view/yoga-anatomy-quiz"),
                Article(context.getString(R.string.artTitle9), "Yoga International", "https://yogainternational.com/article/view/improve-digestion-with-food"),
                Article(context.getString(R.string.artTitle10), "Everyday Health", "https://www.everydayhealth.com/yoga/does-yoga-count-as-exercise/"),
                Article(context.getString(R.string.artTitle11), "Everyday Health", "https://www.everydayhealth.com/yoga/minute-back-to-basics-yoga-flow-for-beginners/"),
                Article(context.getString(R.string.artTitle12), "Everyday Health", "https://www.everydayhealth.com/yoga/can-yoga-lower-your-risk-of-getting-sick/"),
                Article(context.getString(R.string.artTitle13), "MIU", "https://www.miu.edu/blog/2017/01/11/is-meditation-reducing-violence-at-this-chicago-school/"),
                Article(context.getString(R.string.artTitle14), "MIU", "https://www.miu.edu/blog/2016/04/15/tm-ideal-for-millennials-says-inverse-com/"),
                Article(context.getString(R.string.artTitle15), "MIU", "https://www.miu.edu/blog/2015/08/25/flyogi-an-interview-with-miu-alumni-jason-aviles/"),
                Article(context.getString(R.string.artTitle16), "MIU", "https://research.miu.edu/center-for-brain-consciousness-and-cognition/transcendental-consciousness/"),
                Article(context.getString(R.string.artTitle17), "MIU", "https://research.miu.edu/maharishi-effect/"),
                Article(context.getString(R.string.artTitle18), "MIU", "https://research.miu.edu/maharishi-effect/peaceful-body-peaceful-mind-peaceful-world/"),
                Article(context.getString(R.string.artTitle19), "Equip", "https://www.equip.org/articles/transcendental-meditation-in-the-new-millenium-part-one/"),
                Article(context.getString(R.string.artTitle20), "Equip", "https://www.equip.org/articles/transcendental-meditation-in-the-new-millenium-part-two/"),
                Article(context.getString(R.string.artTitle21), "TM Home", "https://tmhome.com/book-review-maharishis-yoga/"),
                Article(context.getString(R.string.artTitle22), "Meru", "https://www.meru.international/transcendental-meditation/five-benefits-of-practising-maharishi-yoga-asanas/"),
                Article(context.getString(R.string.artTitle23), "Help Guide", "https://www.helpguide.org/articles/healthy-eating/healthy-eating.htm"),
                Article(context.getString(R.string.artTitle24), "HSPH", "https://www.hsph.harvard.edu/nutritionsource/healthy-eating-plate/"),
                Article(context.getString(R.string.artTitle25), "Aetna", "https://www.aetna.com/health-guide/food-affects-mental-health.html"),
                Article(context.getString(R.string.artTitle26), "UCSF Health", "https://www.ucsfhealth.org/education/top-ten-foods-for-health")
            )
        }
    }
}