package com.admin.theapp.firebase

import com.google.firebase.storage.FirebaseStorage
import com.theapp.repository.Storage
import com.theapp.tools.Logger
import io.reactivex.Maybe
import javax.inject.Inject

class FirebaseStorageImpl @Inject internal constructor(private val logger: Logger) : Storage {

    private val storageReference = FirebaseStorage.getInstance().reference

    override fun getBytes(name: String): Maybe<ByteArray> {
        var bytes: ByteArray? = null
        storageReference.child(PATH_TO_IMAGE + name)
                .getBytes(ONE_MEGABYTE)
                .addOnSuccessListener { bytes = it }
                .addOnFailureListener { e -> logger.e(LOG_TAG, "Failed to load data: ${e.message}") }
        return Maybe.fromCallable { bytes ?: ByteArray(0) }
    }

    companion object {
        private val LOG_TAG = FirebaseStorageImpl::class.java.simpleName
        private val PATH_TO_IMAGE = "hotels/"
        private val ONE_MEGABYTE = (1024 * 1024).toLong()
    }
}
