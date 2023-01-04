package leodenault.synced.player

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent.VC_MEDIA_NEXT
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent.VC_MEDIA_PLAY
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent.VC_MEDIA_PREVIOUS
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent.VC_MEDIA_STOP
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener

class MediaKeyEventHandler(
  val playCurrentTrack: () -> Unit,
  val playNextTrack: () -> Unit,
  val playPreviousTrack: () -> Unit,
  val stopCurrentTrack: () -> Unit
) : NativeKeyListener {

  override fun nativeKeyPressed(nativeEvent: NativeKeyEvent) {
    when (nativeEvent.keyCode) {
      VC_MEDIA_PLAY -> playCurrentTrack()
      VC_MEDIA_NEXT -> playNextTrack()
      VC_MEDIA_PREVIOUS -> playPreviousTrack()
      VC_MEDIA_STOP -> stopCurrentTrack()
    }
  }
}