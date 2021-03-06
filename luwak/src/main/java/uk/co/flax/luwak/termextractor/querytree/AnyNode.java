package uk.co.flax.luwak.termextractor.querytree;

import uk.co.flax.luwak.termextractor.QueryTerm;

import java.util.Set;

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

public class AnyNode extends QueryTree {

    private final String reason;

    public AnyNode(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean isAny() {
        return true;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public double weight() {
        return 0;
    }

    @Override
    public void collectTerms(Set<QueryTerm> termsList) {
        termsList.add(new QueryTerm("__any__", reason, QueryTerm.Type.ANY));
    }

    @Override
    public boolean advancePhase(float minWeight) {
        return false;
    }

    @Override
    public void visit(QueryTreeVisitor visitor, int depth) {
        visitor.visit(this, 0);
    }
}
