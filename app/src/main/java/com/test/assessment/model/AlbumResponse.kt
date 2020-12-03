package com.test.assessment.model

data class AlbumResponse (
    val resultCount: Long,
    val results: List<Result>
)

data class Result (
    val wrapperType: String? = null,
    val collectionType: String? = null,
    val artistId: Int? = null,
    val collectionId: Int? = null,
    val amgArtistId: Int? = null,
    val artistName: String? = null,
    val collectionName: String? = null,
    val collectionCensoredName: String? = null,
    val artistViewURL: String? = null,
    val collectionViewURL: String? = null,
    val artworkUrl60: String? = null,
    val artworkUrl100: String? = null,
    val collectionPrice: Double? = null,
    val collectionExplicitness: String? = null,
    val trackCount: Long? = null,
    val copyright: String? = null,
    val country: String? = null,
    val currency: String? = null,
    val releaseDate: String? = null,
    val primaryGenreName: String? = null,
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
