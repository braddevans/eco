package com.willfp.eco.internal.lookup

import com.willfp.eco.core.lookup.LookupHandler
import com.willfp.eco.core.lookup.SegmentParser
import com.willfp.eco.core.lookup.Testable

class SegmentParserUseIfPresent : SegmentParser("?") {
    override fun <T : Testable<*>> handleSegments(segments: Array<out String>, handler: LookupHandler<T>): T {
        for (option in segments) {
            val lookup = handler.parseKey(option)
            if (handler.validate(lookup)) {
                return lookup
            }
        }

        return handler.failsafe
    }
}
