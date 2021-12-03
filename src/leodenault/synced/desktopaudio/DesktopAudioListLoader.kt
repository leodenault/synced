package leodenault.synced.desktopaudio

import dagger.Reusable
import leodenault.synced.util.Data
import java.io.File
import javax.inject.Inject

@Reusable
class DesktopAudioListLoader @Inject constructor(private val topLevelDirectory: Data<File>) {
  fun loadAudio(): List<File> =
    findAudioFilesRecursively(listOf(topLevelDirectory.value), emptyList())

  private tailrec fun findAudioFilesRecursively(
    dirs: List<File>,
    audioFiles: List<File>
  ): List<File> {
    val children = dirs.asSequence().flatMap {
      it.listFiles()?.toList() ?: emptyList()
    }
    val childAudioFiles = children.filter { child ->
      child.isFile && VALID_FILE_EXTENSIONS.any { child.name.endsWith(it) }
    }.toList()
    val childDirs = children.filter { it.isDirectory }.toList()

    if (childDirs.isEmpty()) {
      return childAudioFiles + audioFiles
    }

    return findAudioFilesRecursively(childDirs, childAudioFiles + audioFiles)
  }

  companion object {
    private val VALID_FILE_EXTENSIONS = listOf(".mp3", ".wav", ".ogg")
  }
}