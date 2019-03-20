package br.com.lardopet.util

import android.util.Base64
import org.json.JSONObject

    fun String.getTokenData(): JSONObject =
            JSONObject(String(Base64.decode(this.split("\\.".toRegex())[1], Base64.URL_SAFE),
                    Charsets.UTF_8))

