package me.sangoh.clone.toss.android.model.type

data class PhoneNumber(
    val first: Int, // area code (3 digits)
    val second: Int, // exchange  (3 digits OR 4 digits)
    val third: Int // extension (4 digits)
)