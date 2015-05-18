/*
 * Copyright 2014, The OpenNMS Group
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.opennms.newts.api.search;

public class QueryBuilder {

    private QueryBuilder() { }

    public static Query matchAllValues(String... values) {
        final BooleanQuery query = new BooleanQuery();
        Operator op = Operator.OR;
        for (final String value : values) {
            query.add(new TermQuery(new Term(value)), op);
            op = Operator.AND;
        }
        return query;
    }

    public static Query matchAnyValue(String... values) {
        final BooleanQuery query = new BooleanQuery();
        for (final String value : values) {
            query.add(new TermQuery(new Term(value)), Operator.OR);
        }
        return query;
    }
}
