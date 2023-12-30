package util

class FileManager {
    companion object {
        private const val STANDARD_PATH = "/C:/Users/54616/IdeaProjects/DocPassNeo/files"

        fun loadSaveFile(path: String = STANDARD_PATH): String? {
            return null
        }

        fun loadStoredFile(name: String, path: String = STANDARD_PATH): String? {
            return null
        }

        fun writeSaveFile(path: String = STANDARD_PATH): Boolean {
            return false
        }

        fun writeStoredFile(name: String, path: String = STANDARD_PATH): Boolean {
            return false
        }
    }
}