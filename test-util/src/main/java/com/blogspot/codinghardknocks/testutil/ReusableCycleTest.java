/*
 * Copyright 2011 Kirby C. Bohling
 *
 * This code is licensed under the Apache License, Version 2.0 (the "License")
 * terms.  You may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.blogspot.codinghardknocks.testutil;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jdepend.framework.JDepend;
import jdepend.framework.JavaPackage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Re-usable test to verify that there are no package cycles.
 *
 * @author kbohling
 *
 */
public class ReusableCycleTest {

    private JDepend jdepend;

    @Before
    public void setUp() throws IOException {
        jdepend = new JDepend();
        jdepend.addDirectory("target/classes");
        jdepend.addDirectory("target/test-classes");
    }

    /**
     * Tests that a package dependency cycle does not
     * exist for any of the analyzed packages.
     */
    @Test
    public void testAllPackages() {
        @SuppressWarnings("unchecked")
        Collection<JavaPackage> packages = jdepend.analyze();

        for (JavaPackage p: packages) {
            if (computeCycle(p)) {
                System.out.println("Package contains cycle: " + p.getName());
            }
        }
        assertTrue("Cycles exist", !jdepend.containsCycles());
    }

    public boolean computeCycle(JavaPackage orig) {
        Set<JavaPackage> pending = new HashSet<JavaPackage>();
        Set<JavaPackage> visited = new HashSet<JavaPackage>();

        @SuppressWarnings("unchecked")
        Collection<JavaPackage> tmp = orig.getEfferents();
        pending.addAll(tmp);

        while (!pending.isEmpty()) {
            JavaPackage curr = pending.iterator().next();

            visited.add(curr);

            @SuppressWarnings("unchecked")
            Collection<JavaPackage> nextSet = curr.getEfferents();
            if (nextSet.contains(orig)) {
                return true;
            }

            nextSet.removeAll(visited);
            pending.addAll(nextSet);
            pending.remove(curr);

        }

        return false;
    }
}
