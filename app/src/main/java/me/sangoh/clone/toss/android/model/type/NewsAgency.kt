package me.sangoh.clone.toss.android.model.type

enum class NewsAgency(
    val title: String
) {
    SKT("SKT"),
    KT("KT"),
    LG_U("LG U+"),
    SKT_A("SKT 알뜰폰"),
    KT_A("KT 알뜰폰"),
    LG_U_A("LG U+ 알뜰폰")
    ;

    override fun toString(): String {
        return title
    }
}