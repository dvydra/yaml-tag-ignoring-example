package com.ivydra.parser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RubyGeneratedYamlParserTest {

    @Test
    public void shouldParseBasicYaml() {
        RubyGeneratedYamlParser yaml = new RubyGeneratedYamlParser();
        Map result = yaml.load("---\n    foo: bar\n");
        assertThat((String)result.get("foo"), equalTo("bar"));
    }

    @Test
    public void testShouldParseRubyGeneratedYaml() {
        RubyGeneratedYamlParser yaml = new RubyGeneratedYamlParser();
        Map result = yaml.load("---\n    bar: !ruby/thing narf\n    foo: !ruby/nerf/nerf/nerf [bar, baz]");
        ArrayList<String> list = (ArrayList<String>)result.get("foo");
        assertThat(list.get(0), equalTo("bar"));
        assertThat(list.get(1), equalTo("baz"));
        assertThat((String)result.get("bar"), equalTo("narf"));
    }
}
