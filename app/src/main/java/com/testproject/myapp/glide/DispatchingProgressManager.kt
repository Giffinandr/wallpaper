package com.testproject.myapp.glide

import android.os.Handler
import android.os.Looper
import okhttp3.HttpUrl

class DispatchingProgressManager internal constructor(): ResponseProgressListener{

    companion object{
        private val PROGRESS = HashMap<String?, Long>()
        private val LISTENER = HashMap<String?, UIonProgressListener>()

        internal fun expect(url: String?, listener: UIonProgressListener) {
            LISTENER[url] = listener
        }

        internal fun forget(url: String) {
            LISTENER.remove(url)
            PROGRESS.remove(url)
        }
    }

    private val handler: Handler = Handler(Looper.getMainLooper())
    override fun update(url: HttpUrl, bytesRead: Long, contentLength: Long) {
        val key = url.toString()
        val listener = LISTENER[key] ?: return
        if (contentLength <= bytesRead) forget(key)
        if (needsDispatch(key, bytesRead, contentLength,
            listener.granularityPercentage)) {
            handler.post { listener.omProgress(bytesRead, contentLength) }
        }
    }

    private fun needsDispatch(key: String, current: Long, total: Long,
    granularity: Float): Boolean {
        if (granularity == 0f || current == 0L || total == current)
            return true
        val percent = 100f * current / total
        val currentProgress = (percent / granularity).toLong()
        val lastProgress = PROGRESS[key]

        return if (lastProgress == null || currentProgress != lastProgress) {
            PROGRESS[key] = currentProgress
            true
        } else {
            false
        }
    }
}