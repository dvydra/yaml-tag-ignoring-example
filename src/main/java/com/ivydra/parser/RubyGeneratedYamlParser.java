package com.ivydra.parser;

import org.yaml.snakeyaml.Yaml;

import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;


public class RubyGeneratedYamlParser {
    public Map load(String input) {
        Yaml yaml = new Yaml(new TagIgnoringConstructor());

        return (Map) yaml.load(input);


    }
}
