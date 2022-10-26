package leodenault.synced.desktopaudio

import com.sedmelluq.discord.lavaplayer.container.MediaContainer
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
        val childAudioFiles = children.filter { child ->
            child.isFile && VALID_FILE_EXTENSIONS.any { child.name.endsWith(it) }
        }.map { AudioFile(it, isFileSupported(it)) }.toList()
        val childDirs = children.filter { it.isDirectory }.toList()

        if (childDirs.isEmpty()) {
            return childAudioFiles + audioFiles
        }

        return findAudioFilesRecursively(childDirs, childAudioFiles + audioFiles)
    }

    private fun isFileSupported(file: File): Boolean = kotlin.runCatching {
        val probeResult: MediaContainerProbe? = MediaContainer.asList().firstOrNull {
            // The probe returns null when the media container doesn't match.
            it.probe(
                AudioReference(file.canonicalPath, file.canonicalPath),
                LocalSeekableInputStream(file)
            )?.isSupportedFile ?: false
        }
        probeResult != null
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
        private val VALID_FILE_EXTENSIONS = listOf(".mp3", ".wav", ".ogg")
    }
}