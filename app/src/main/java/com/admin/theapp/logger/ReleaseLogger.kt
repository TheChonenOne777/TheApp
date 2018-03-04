package com.admin.theapp.logger

import com.theapp.tools.Logger

class ReleaseLogger : Logger {
    override fun d(tag: String, msg: String) { // default implementation ignored
    }

    override fun e(tag: String, msg: String) { // default implementation ignored
    }

    override fun e(tag: String, msg: String, tr: Throwable) { // default implementation ignored
    }

    override fun i(tag: String, msg: String) { // default implementation ignored
    }

    override fun v(tag: String, msg: String) { // default implementation ignored
    }

    override fun w(tag: String, msg: String) { // default implementation ignored
    }
}
