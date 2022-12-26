package leodenault.synced.desktopaudio

import com.sedmelluq.discord.lavaplayer.container.MediaContainer
import com.sedmelluq.discord.lavaplayer.container.MediaContainerHints
import com.sedmelluq.discord.lavaplayer.container.MediaContainerProbe
import com.sedmelluq.discord.lavaplayer.source.local.LocalSeekableInputStream
import com.sedmelluq.discord.lavaplayer.track.AudioReference
import dagger.Reusable
import leodenault.synced.util.Data
import java.io.File
import java.util.logging.Logger
import javax.inject.Inject

@Reusable
class DesktopAudioListLoader @Inject constructor(private val topLevelDirectory: Data<File>) {
  fun loadAudio(): List<AudioFile> =
    findAudioFilesRecursively(listOf(topLevelDirectory.value), emptyList())

  private tailrec fun findAudioFilesRecursively(
    dirs: List<File>,
    audioFiles: List<AudioFile>
  ): List<AudioFile> {
    val children = dirs.asSequence().flatMap {
      it.listFiles()?.toList() ?: emptyList()
    }
    val mediaContainers = MediaContainer.values()
    val childAudioFiles = children.filter { child ->
      child.isFile
          && child.canRead()
          && mediaContainers.any { child.extension == it.fileExtension }
    }.map { child ->
      AudioFile(child, mediaContainers.any {
        it.probe.fullySupports(
          AudioReference(child.canonicalPath, child.canonicalPath),
          LocalSeekableInputStream(child)
        )
      })
    }.toList()
    val childDirs = children.filter { it.isDirectory }.toList()

    if (childDirs.isEmpty()) {
      return childAudioFiles + audioFiles
    }

    return findAudioFilesRecursively(childDirs, childAudioFiles + audioFiles)
  }

  /**
   * Lavaplayer doesn't seem to have a way to programmatically validate file extensions, so we use
   * an exhaustive `when` statement to help cover all extensions supported by the library.
   *
   * @return the file extension supported by this [MediaContainer] or `null` if unsupported.
   */
  private val MediaContainer.fileExtension: String?
    get() = when (this) {
      MediaContainer.WAV -> "wav"
      MediaContainer.MKV -> "mka"
      MediaContainer.MP4 -> "mp4"
      MediaContainer.FLAC -> "flac"
      MediaContainer.OGG -> "ogg"
      MediaContainer.M3U -> ".m3u"
      MediaContainer.PLS -> null
      MediaContainer.PLAIN -> null
      MediaContainer.MP3 -> "mp3"
      MediaContainer.ADTS -> "adts"
      MediaContainer.MPEGADTS -> "ts"
    }

  /**
   * Returns true if a file is supported by a [MediaContainerProbe] using a more thorough check than
   * the original [MediaContainerDetectionResult.isSupportedFile].
   */
  private fun MediaContainerProbe.fullySupports(
    audioReference: AudioReference,
    inputStream: LocalSeekableInputStream
  ): Boolean = kotlin.runCatching {
    // The probe returns null when the media container doesn't match.
    probe(audioReference, inputStream)?.isSupportedFile ?: false
  }.getOrElse {
    // Log this as info since sometimes the exception is WAI according to the library
    // maintainer.
    LOGGER.fine(
      "Exception was thrown while attempting to load audio with message \"${it.message}\"." +
          "\n${it.stackTraceToString()}"
    )
    false
  }

  companion object {
    private val LOGGER =
      Logger.getLogger("leodenault.synced.desktopaudio.DesktopAudioListLoader")
  }
}