package com.sohnyi.liangmi.extensions

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils

/**
 * 返回 String 的 SHA256 值
 */
val String.sha256Hex: String
    get() = String(Hex.encodeHex(DigestUtils.sha256(this)))