package com.ivydra.parser;

import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.Construct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.Tag;

public class TagIgnoringConstructor extends Constructor {
    private Construct original;

    public TagIgnoringConstructor() {
        original = this.yamlConstructors.get(null);
        this.yamlConstructors.put(null, new IgnoringConstruct());
    }

    private class IgnoringConstruct extends AbstractConstruct {
        public Object construct(Node node) {
            if (node.getTag().startsWith("!KnownTag")) {
                return original.construct(node);
            } else {
                switch (node.getNodeId()) {
                case scalar:
                    return yamlConstructors.get(Tag.STR).construct(node);
                case sequence:
                    return yamlConstructors.get(Tag.SEQ).construct(node);
                case mapping:
                    return yamlConstructors.get(Tag.MAP).construct(node);
                default:
                    throw new YAMLException("Unexpected node");
                }
            }
        }
    }
}