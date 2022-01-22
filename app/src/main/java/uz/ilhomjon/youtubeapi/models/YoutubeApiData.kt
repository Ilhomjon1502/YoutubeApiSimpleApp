package uz.ilhomjon.youtubeapi.models

data class YoutubeApiData(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo,
    val regionCode: String
)