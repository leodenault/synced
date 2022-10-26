package leodenault.synced.audioselector

import dagger.Reusable
import leodenault.synced.desktopaudio.AudioFile
import javax.inject.Inject

class AudioTrackViewModel private constructor(file: AudioFile) {
    val name: String = file.javaFile.name
    val path: String = file.javaFile.canonicalPath
    val key: String = file.javaFile.canonicalPath
    val isSupported: Boolean = file.isSupported

    @Reusable
    class Factory @Inject constructor() {
        fun create(file: AudioFile) = AudioTrackViewModel(file)
    }
}