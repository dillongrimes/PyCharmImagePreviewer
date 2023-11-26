package com.github.dillongrimes.pycharmimagepreviewer

import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import com.intellij.psi.xml.XmlElementType.XML_ATTRIBUTE


fun getSrcContent (element: PsiElement): String? {
  for (item in element.children){
    if (item.elementType == XML_ATTRIBUTE) {
      if (item.firstChild.text in arrayOf("src", "srcset")){
        return item.lastChild.text
      }
    }
  }
  return null
}

class HTMLLineMarkerContributor : RunLineMarkerContributor() {
  override fun getInfo(element: PsiElement): Info? {
    if (element.toString() in arrayOf("HtmlTag:img", "HtmlTag:source")) {
      val imageUrl = getSrcContent(element)
      if (imageUrl != null ) {
        return getLineMaker(removeUrlQuotes(imageUrl))
     }
      return null
    }
    return null
  }
}