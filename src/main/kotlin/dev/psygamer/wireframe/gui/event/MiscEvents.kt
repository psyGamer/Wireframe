package dev.psygamer.wireframe.gui.event

import java.nio.file.Path

data class FilesDroppedEvent(val filePaths: List<Path>) : GUIEvent()