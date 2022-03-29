package com.myappventure.app.base

sealed class Message {
    class Toast(val message: String?) : Message()
    class LongToast(val message: String?) : Message()
    class Dialog(val message: String?) : Message()
}