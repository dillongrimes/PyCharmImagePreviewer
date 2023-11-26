package com.github.dillongrimes.pycharmimagepreviewer

//fun isImageUrl(url: String): Boolean {
//  val regex = "^(http|https)://.*(jpg|jpeg|png|gif|bmp|webp)$".toRegex(RegexOption.IGNORE_CASE)
//  return regex.containsMatchIn(url)
//}

fun isAemUrl(url: String): Boolean {
  return url.startsWith("https://img.uline.com")
}

fun removeUrlQuotes(url: String): String {
  return url.replace("\"", "").replace("\'", "")
}
