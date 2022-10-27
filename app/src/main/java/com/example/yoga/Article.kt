package com.example.yoga

data class Article(var title: String, var source: String, var link: String = "") {
    companion object {
        var articles = arrayListOf(
            Article("Yoga benefits", "Harvard Medical School", "https://www.health.harvard.edu/staying-healthy/yoga-benefits-beyond-the-mat"),
            Article("Why Yoga Is Good for Your Body and Brain", "Greater Good Magazine", "https://greatergood.berkeley.edu/article/item/why_yoga_is_good_for_your_body_and_brain_according_to_science"),
            Article("Yoga for everyone", "NY Times", "https://greatergood.berkeley.edu/article/item/why_yoga_is_good_for_your_body_and_brain_according_to_science"),
            Article("16 Benefits of Yoga That Are Supported by Science", "Healthline", "https://www.healthline.com/nutrition/13-benefits-of-yoga"),
            Article("A 6-Pose Practice for Challenging Times", "Yoga International", "https://www.healthline.com/nutrition/13-benefits-of-yoga"),
            Article("Relieve Neck Tension With This Short Therapeutic Practice", "Yoga International", "https://yogainternational.com/article/view/relieve-neck-tension-with-this-short-therapeutic-practice"),
            Article("Sweet Coconut and Turmeric Milk", "Yoga International", "https://yogainternational.com/article/view/sweet-coconut-and-turmeric-milk"),
            Article("How Well Do You Know Yoga Anatomy", "Yoga International", "https://yogainternational.com/article/view/yoga-anatomy-quiz"),
            Article("5 Ways to Use Food to Improve Digestion", "Yoga International", "https://yogainternational.com/article/view/improve-digestion-with-food"),
            Article("Does Yoga Count as Exercise", "Everyday Health", "https://www.everydayhealth.com/yoga/does-yoga-count-as-exercise/"),
            Article("5-Minute Back-to-Basics Yoga", "Everyday Health", "https://www.everydayhealth.com/yoga/minute-back-to-basics-yoga-flow-for-beginners/"),
            Article("Can Yoga Lower Your Risk of Getting Sick?", "Everyday Health", "https://www.everydayhealth.com/yoga/can-yoga-lower-your-risk-of-getting-sick/"),
            Article("Is Meditation Reducing Violence at This Chicago School?", "MIU", "https://www.miu.edu/blog/2017/01/11/is-meditation-reducing-violence-at-this-chicago-school/"),
            Article("Transcendental Meditation Ideal for Millennials, Says Inverse.com", "MIU", "https://www.miu.edu/blog/2016/04/15/tm-ideal-for-millennials-says-inverse-com/"),
            Article("MIU Alum Brings Yoga to Inner-City Wilmington", "MIU", "https://www.miu.edu/blog/2015/08/25/flyogi-an-interview-with-miu-alumni-jason-aviles/"),
            Article("Physiological Patterns of Transcendental Consciousness", "MIU", "https://research.miu.edu/center-for-brain-consciousness-and-cognition/transcendental-consciousness/"),
            Article("Maharishi Effect", "MIU", "https://research.miu.edu/maharishi-effect/"),
            Article("Peaceful Body, Peaceful Mind, Peaceful World", "MIU", "https://research.miu.edu/maharishi-effect/peaceful-body-peaceful-mind-peaceful-world/"),
            Article("Transcendental Meditation in the New Millennium (Part One)", "Equip", "https://www.equip.org/articles/transcendental-meditation-in-the-new-millenium-part-one/"),
            Article("Transcendental Meditation in the New Millennium (Part two)", "Equip", "https://www.equip.org/articles/transcendental-meditation-in-the-new-millenium-part-two/"),
            Article("Maharishi’s Yoga: The foundations of true practice", "TM Home", "https://tmhome.com/book-review-maharishis-yoga/"),
            Article("Five benefits of practicing Maharishi Yoga Asanas", "Meru", "https://www.meru.international/transcendental-meditation/five-benefits-of-practising-maharishi-yoga-asanas/"),
            Article("Healthy Eating", "Help Guide", "https://www.helpguide.org/articles/healthy-eating/healthy-eating.htm"),
            Article("Healthy Eating Plate", "HSPH", "https://www.hsph.harvard.edu/nutritionsource/healthy-eating-plate/"),
            Article("Food for your mood", "Aetna", "https://www.aetna.com/health-guide/food-affects-mental-health.html"),
            Article("Top 10 foods for health", "UCSF Health", "https://www.ucsfhealth.org/education/top-ten-foods-for-health")
        )
    }

}