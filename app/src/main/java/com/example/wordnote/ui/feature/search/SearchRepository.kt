package com.example.wordnote.ui.feature.search
import com.example.wordnote.ui.model.Definition
import com.example.wordnote.ui.model.Meaning
import com.example.wordnote.ui.model.WordResult


class SearchRepository(private val searchApi: SearchApi) {

    suspend fun getWord(word: String): List<WordResult> {
        val response = searchApi.getWord(word)
        if (response.isSuccessful) {
            val wordResults = mutableListOf<WordResult>()
            val responseWord = response.body()
            responseWord?.forEach { wordData ->
                val wordResult = WordResult(
                    word = wordData.word,
                    meanings = wordData.meanings.map { meaningData ->
                        Meaning(
                            definitions = meaningData.definitions.map { definitionData ->
                                Definition(
                                    definition = definitionData.definition
                                )
                            }
                        )
                    }
                )
                wordResults.add(wordResult)
            }
            return wordResults
        } else {
            return emptyList()
        }
    }

}
