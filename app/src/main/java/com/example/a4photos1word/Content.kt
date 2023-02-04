package com.example.a4photos1word

object Content {
    const val PREFS = "4Photos1Word"
    const val LEVEL = "currentLevel"
    const val CYCLE = "levelCycle"
    fun getQuestion() : List<Questions>{
        return listOf(
            Questions(
                id = 1,
                images = listOf(
                        R.drawable.photo1_1,
                        R.drawable.photo1_2,
                        R.drawable.photo1_3,
                        R.drawable.photo1_4
                ),
                answer = "ХОЛОД"
            ),
            Questions(
                id = 2,
                images = listOf(
                    R.drawable.photo2_1,
                    R.drawable.photo2_2,
                    R.drawable.photo2_3,
                    R.drawable.photo2_4
                ),
                answer = "ГРОМКО"
            ),
            Questions(
                id = 3,
                images = listOf(
                    R.drawable.photo3_1,
                    R.drawable.photo3_2,
                    R.drawable.photo3_3,
                    R.drawable.photo3_4
                ),
                answer = "ГОРЯЧИЙ"
            ),
            Questions(
                id = 4,
                images = listOf(
                    R.drawable.photo4_1,
                    R.drawable.photo4_2,
                    R.drawable.photo4_3,
                    R.drawable.photo4_4
                ),
                answer = "МУЗЫКА"
            ),
            Questions(
                id = 5,
                images = listOf(
                    R.drawable.photo5_1,
                    R.drawable.photo5_2,
                    R.drawable.photo5_3,
                    R.drawable.photo5_4
                ),
                answer = "ТОЧКА"
            )
        )
    }
}