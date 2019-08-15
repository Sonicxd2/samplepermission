package ru.sonicxd2.samplepermission;

import java.util.HashMap;
import java.util.Map;

class Node {
    Map<String, Node> subNodes = new HashMap<>();
    boolean containsStarNode = false;
    boolean value;

    public Node(boolean value) {
        this.value = value;
    }

    public boolean isGranted(String permission) {
        if (permission.isEmpty()) {
            return value;
        }

        if (containsStarNode) {
            return subNodes.get("*").isGranted("");
        }

        int dotId = permission.indexOf('.');
        if (dotId == -1) {
            Node node = subNodes.get(permission);
            return node != null && node.isGranted("");
        }
        String current = permission.substring(0, dotId);
        Node node = subNodes.get(current);
        return node != null && node.isGranted(permission.substring(dotId + 1));
    }
}
