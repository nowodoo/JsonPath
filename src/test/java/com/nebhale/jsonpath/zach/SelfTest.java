/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nebhale.jsonpath.zach;

import com.nebhale.jsonpath.JsonPath;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public final class SelfTest {
    @Test
    public void test() {
        JsonPath namePath = JsonPath.compile("$.jobConf.srcTable");
        List<String> namesSst = namePath.read("", List.class);
        List<String> namesSit = namePath.read("", List.class);

        System.out.println(namesSst);
        System.out.println(namesSit);

        Map<String, Integer> sstCount = new HashMap<String, Integer>();
        Map<String, Integer> sitCount = new HashMap<String, Integer>();

        System.out.println("\nbased on sst, find something lost in sit:"+namesSst.size());
        for (String tsst:namesSst){
            if (namesSit.contains(tsst)) {
                System.out.println("include:"+tsst);
            }else{
                System.out.println("!exclude:"+tsst);
            }

            Integer tCount = (null == sstCount.get(tsst)) ? 0+1 : sstCount.get(tsst)+1;
            sstCount.put(tsst, tCount);


        }

        System.out.println(" \nbased on sit, find something lost in sst:"+namesSit.size());
        for (String tsit:namesSit){
            if (namesSst.contains(tsit)) {
                System.out.println("include:"+tsit);
            }else{
                System.out.println("!exclude:"+tsit);
        }
            Integer tCount = (null == sitCount.get(tsit)) ? 0+1 : sstCount.get(tsit) + 1;
            sitCount.put(tsit, tCount);

        }

        System.out.println("\n");
        System.out.println("sst count:");
        for (String key:sstCount.keySet()){
            System.out.println(key + "-" + sstCount.get(key));
        }
        System.out.println("\n");
        System.out.println("sit count:");
        for (String key:sitCount.keySet()){
            System.out.println(key + "-" + sitCount.get(key));
        }
    }
}
