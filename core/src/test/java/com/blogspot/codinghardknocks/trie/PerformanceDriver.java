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
package com.blogspot.codinghardknocks.trie;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 *
 * <pre>
 * Word Count: 178692
 * Elapsed Iteration:   10980
 * Elapsed Char Seq:    868244
 * Elapsed String Trie: 692991
 * Elapsed RB-Tree:     871928
 * </pre>
 * @author kbohling
 *
 */
public class PerformanceDriver {

    public static void main(String[] args) throws Exception {
        BufferedReader bufReader = null;
        List<String> words = new ArrayList<String>(178692);
        try {
            InputStream stream = PerformanceDriver.class.getResourceAsStream("Lexicon.txt");
            Reader reader = new InputStreamReader(stream);
            bufReader = new BufferedReader(reader);

            String line;
            while ((line = bufReader.readLine()) != null) {
                words.add(line);
            }

//            words = words.subList(0, 10000);
            Collections.shuffle(words);
            System.out.println("Word Count: " + words.size());

            StructureTypes[] ops = new StructureTypes[] {
                    StructureTypes.ITERATE,
                    StructureTypes.CHAR_SEQ_TRIE,
                    StructureTypes.STRING_TRIE,
                    StructureTypes.RB_TREE,
                    };

            for (StructureTypes op : ops ) {
                op.execute(words);
            }

            long iterationElapsed = timeExecutions(words, StructureTypes.ITERATE);
            long charSeqElapsed = timeExecutions(words, StructureTypes.CHAR_SEQ_TRIE);
            long stringTrieElapsed = timeExecutions(words, StructureTypes.STRING_TRIE);
            long rbElapsed =  timeExecutions(words, StructureTypes.RB_TREE);

            System.out.println("Elapsed Iteration:   " + TimeUnit.MILLISECONDS.convert(iterationElapsed,
                    TimeUnit.NANOSECONDS));
            System.out.println("Elapsed Char Seq:    " + TimeUnit.MILLISECONDS.convert(charSeqElapsed,
                    TimeUnit.NANOSECONDS));
            System.out.println("Elapsed String Trie: " + TimeUnit.MILLISECONDS.convert(stringTrieElapsed,
                    TimeUnit.NANOSECONDS));
            System.out.println("Elapsed RB-Tree:     " + TimeUnit.MILLISECONDS.convert(rbElapsed,
                    TimeUnit.NANOSECONDS));


        } finally {
            if (bufReader != null) {
                bufReader.close();
            }
        }
    }

    private static long timeExecutions(List<String> words, StructureTypes type) {
        int iterations = 1000;
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; ++i) {
            type.execute(words);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private enum StructureTypes {
        ITERATE {
            @Override
            public void execute(List<String> words) {
                countNonNull(words);
            }
        },
        CHAR_SEQ_TRIE {
            @Override
            public void execute(List<String> words) {
                CharSequenceTrieMap<Object> charSeqTrie = new CharSequenceTrieMap<Object>();
                populateCharSeqTrie(words, charSeqTrie);
            }
        },
        STRING_TRIE {
            @Override
            public void execute(List<String> words) {
                StringTrieMap<Object> stringTrie = new StringTrieMap<Object>();
                populateStringTrie(words, stringTrie);
            }
        },
        RB_TREE {
            @Override
            public void execute(List<String> words) {
                SortedMap<String, Object> map = new TreeMap<String, Object>();
                populateTree(words, map);
            }
        };

        public abstract void execute(List<String> words);

    }


    /**
     * @param words
     * @param map
     */
    private static void populateTree(List<String> words, SortedMap<String, Object> map) {
        for (String word : words) {
            map.put(word, null);
        }

        for (String word : words) {
            map.get(word);
        }
        map.clear();
    }

    private static int countNonNull(List<String> words) {
        int count = 0;
        for (String word : words) {
            if (word != null) {
                ++count;
            }
        }

        for (String word : words) {
            ++count;
        }
        return count;
    }

    private static void populateStringTrie(List<String> words, StringTrieMap<Object> trie) {
        for (String word : words) {
            trie.addIfAbsent(word, null);
        }

        for (String word : words) {
            trie.contains(word);
        }
        trie.clear();
    }

    private static void populateCharSeqTrie(List<String> words, CharSequenceTrieMap<Object> trie) {
        for (String word : words) {
            trie.addIfAbsent(word, null);
        }

        for (String word : words) {
            trie.get(word);
        }
        trie.clear();
    }
}
