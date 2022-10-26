package leodenault.synced.desktopaudio

import java.io.File

/**
 * A file containing audio.
 *
 * @param javaFile the [File] designating where the audio lives.
 * @param isSupported whether the audio is supported by this application.
 */
class AudioFile(val javaFile: File, val isSupported: Boolean)