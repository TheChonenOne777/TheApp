package com.admin.theapp.firebase

import com.google.firebase.storage.FirebaseStorage
import com.theapp.repository.Storage
import com.theapp.tools.Logger
import io.reactivex.Maybe
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class FirebaseStorageImpl @Inject internal constructor(private val logger: Logger) : Storage {

    private val storageReference = FirebaseStorage.getInstance().reference

    override fun getBytes(name: String): Maybe<ByteArray> {
        val subject = PublishSubject.create<ByteArray>()
        storageReference.child(PATH_TO_IMAGE + name)
                .getBytes(ONE_MEGABYTE)
                .addOnSuccessListener { subject.onNext(it) }
                .addOnFailureListener { e -> logger.e(LOG_TAG, "Failed to load data: ${e.message}") }
        return subject.firstElement()
    }

    companion object {
        private val LOG_TAG = FirebaseStorageImpl::class.java.simpleName
        private const val PATH_TO_IMAGE = "hotels/"
        private const val ONE_MEGABYTE = (1024 * 1024).toLong()
    }
}
