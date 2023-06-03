package com.ironxiao.frpc;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class FrpcConfig {
    private ArrayList<Node> nodes;
    private static final String FRPC_INI_PATH = "/data/local/tmp/frpc_test.ini";

    public static final String KEY_SERVER_ADDR = "server_addr";
    public static final String KEY_SERVER_PORT = "server_port";
    public static final String KEY_SERVER_TOKEN = "token";
    //    public static final String KEY_LOCAL_CONFIG_NAME= "server_addr";
    public static final String KEY_LOCAL_TYPE = "type";
    public static final String KEY_LOCAL_IP = "local_ip";
    public static final String KEY_LOCAL_PORT = "local_port";
    public static final String KEY_REMOTE_PORT = "remote_port";

    public FrpcConfig() {
        nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            sb.append(node.toString());
            sb.append("\n");
        }
        return sb.substring(0, sb.lastIndexOf("\n"));
    }

    public void saveTo(String path) {
        String configContent = this.toString();
        try {
            FileWriter fr = new FileWriter(path);
            fr.write(configContent);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static class Node {
        private String name;

        private LinkedHashMap<String, String> nodeInfo;

        public Node(String name) {
            this.name = "[" + name + "]";
            nodeInfo = new LinkedHashMap<>();
        }

        public void add(String key, String value) {
            nodeInfo.put(key, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(name).append("\n");
            Iterator<Map.Entry<String, String>> iterator = nodeInfo.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String subInfo = entry.getKey() + " = " + entry.getValue();
                sb.append(subInfo);
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}
