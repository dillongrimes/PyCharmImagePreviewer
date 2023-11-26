package com.github.dillongrimes.pycharmimagepreviewer

import com.github.dillongrimes.pycharmimagepreviewer.ui.PreviewPopup
import com.github.dillongrimes.pycharmimagepreviewer.ui.previewIcon
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.wm.WindowManager
import com.intellij.util.net.HttpConfigurable
//import org.apache.commons.httpclient.util.HttpURLConnection
//import org.apache.commons.httpclient.util.HttpURLConnection

import java.io.IOException
import java.net.HttpURLConnection
import javax.imageio.ImageIO

fun getLineMaker (imgUrl: String): RunLineMarkerContributor.Info? {
  val isImage = isAemUrl(imgUrl)

  if (!isImage) {
    return null
  }

  val config = HttpConfigurable.getInstance()
  val res = config.openHttpConnection(imgUrl)

  val image = try {
    ImageIO.read(res.inputStream)
  } catch (exception: IOException) {
    return null
  }

  val previewAction = object : AnAction("Preview", "Preview", AllIcons.Debugger.Watch) {
    override fun actionPerformed(e: AnActionEvent) {
      if (res.responseCode == HttpURLConnection.HTTP_OK) {
        PreviewPopup.show(WindowManager.getInstance().suggestParentWindow(null), image)
      }
    }
  }

  return RunLineMarkerContributor.Info(previewIcon, arrayOf(previewAction)) {
    "preview image"
  }
}