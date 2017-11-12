package uk.co.flax.luwak.presearcher;

import java.util.Iterator;
import java.util.Map;

import uk.co.flax.luwak.Matches;
import uk.co.flax.luwak.QueryMatch;

/*
 * Copyright (c) 2014 Lemur Consulting Ltd.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class PresearcherMatches<T extends QueryMatch> implements Iterable<PresearcherMatch<T>> {

    private final Map<String, StringBuilder> matchingTerms;
    public final Matches<T> matcher;

    public PresearcherMatches(Map<String, StringBuilder> matchingTerms, Matches<T> matcher) {
        this.matcher = matcher;
        this.matchingTerms = matchingTerms;
    }

    public PresearcherMatch<T> match(String queryId, String docId) {
        StringBuilder found = matchingTerms.get(queryId);
        if (found != null)
            return new PresearcherMatch<>(queryId, found.toString(), matcher.matches(queryId, docId));
        return null;
    }

    public int getPresearcherMatchCount() {
        return matcher.getPresearcherHits().size();
    }

    @Override
    public Iterator<PresearcherMatch<T>> iterator() {
        final Iterator<String> ids = matchingTerms.keySet().iterator();
        return new Iterator<PresearcherMatch<T>>() {
            @Override
            public boolean hasNext() {
                return ids.hasNext();
            }

            @Override
            public PresearcherMatch<T> next() {
                String id = ids.next();
                return new PresearcherMatch<>(id, matchingTerms.get(id).toString(), matcher.matches(id, ""));
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
