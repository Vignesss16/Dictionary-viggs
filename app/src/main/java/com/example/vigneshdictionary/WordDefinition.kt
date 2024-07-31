package com.example.vigneshdictionary


data class WordDefinition(
    val term: String,
    val phoneticSpelling: String?,
    val meaningsList: List<WordMeaning>,
)

data class WordMeaning(
    val partOfSpeech: String,
    val definitionsList: List<WordDefinitionDetail>,
    val synonymsList: List<String>,
    val antonymsList: List<String>,
)

data class WordDefinitionDetail(
    val meaning: String
)
