package com.example.affirmationapp.data

import com.example.affirmationapp.R;
import com.example.affirmationapp.model.Affirmation;
import android.content.Context;

class Datasource {
    fun loadAffirmation(context: Context): List<Affirmation> {
        val texts = context.resources.obtainTypedArray(R.array.affirmations)
        val images = context.resources.obtainTypedArray(R.array.affirmation_images)

        val list = List(texts.length()) { index ->
            Affirmation(
                texts.getResourceId(index, 0),
                images.getResourceId(index, 0)
            )
        }

        texts.recycle()
        images.recycle()

        return list
    }
}