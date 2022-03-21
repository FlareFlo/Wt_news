package com.example.wt_news

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString


class KeckSocket : WebSocketListener() {
	override fun onOpen(webSocket: WebSocket, response: Response?) {
		println("Sending ws payloads")
		webSocket.send("What's up ?")
		webSocket.send(ByteString.decodeHex("abcd"))
	}

	override fun onMessage(webSocket: WebSocket?, message: String) {
		println("Receive Message: $message")

		var notificationBuilder = NotificationCompat.Builder(ctx, "keck")
			.setSmallIcon(R.drawable.cum_duck_background)
			.setContentTitle("Deez Nutz News")
			.setContentText(message)
			.setPriority(NotificationCompat.PRIORITY_DEFAULT)

		with(NotificationManagerCompat.from(ctx)) {
			notify(1, notificationBuilder.build())
		}
	}

	override fun onMessage(webSocket: WebSocket?, bytes: ByteString) {
		println("Receive Bytes : " + bytes.hex())
	}

	override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
		webSocket.close(CLOSE_STATUS, null)
		println("Closing Socket : $code / $reason")
	}

	override fun onFailure(webSocket: WebSocket?, throwable: Throwable, response: Response?) {
		println("Error : " + throwable.message)
	}

	companion object {
		private const val CLOSE_STATUS = 1000
	}
}