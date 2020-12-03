package com.test.assessment.model

data class AlbumResponse (
    val resultCount: Long,
    val results: List<Result>
)

data class Result (
    val wrapperType: WrapperType,
    val collectionType: CollectionType,
    val artistID: Long,
    val collectionID: Long,
    val amgArtistID: Long? = null,
    val artistName: ArtistName,
    val collectionName: String,
    val collectionCensoredName: String,
    val artistViewURL: String,
    val collectionViewURL: String,
    val artworkUrl60: String,
    val artworkUrl100: String,
    val collectionPrice: Double,
    val collectionExplicitness: CollectionExplicitness,
    val trackCount: Long,
    val copyright: String,
    val country: Country,
    val currency: Currency,
    val releaseDate: String,
    val primaryGenreName: String,
    val contentAdvisoryRating: String? = null
)

enum class ArtistName {
    JackJack,
    JackJohnson,
    JackJohnsonAndFriends,
    JackJohnsonTheRoots,
    JonasBlueJackJack,
    MilkyChanceJackJohnson,
    ZachGillJackJohnson
}

enum class CollectionExplicitness {
    Cleaned,
    Explicit,
    NotExplicit
}

enum class CollectionType {
    Album
}

enum class Country {
    Usa
}

enum class Currency {
    Usd
}

enum class WrapperType {
    Collection
}
