package ru.sonicxd2.samplepermission;

public class PermissionPlayer {
    private Node root = new Node(false);

    public boolean isGranted(String permission) {
        return root.isGranted(permission);
    }

    public boolean hasAllPermissions() {
        return root.containsStarNode && root.subNodes.get("*").isGranted("");
    }

    public void grant(String permission) {
        setPermission(permission, true);
    }

    public void revoke(String permission) {
        setPermission(permission, false);
    }


    private void setPermission(String permission, boolean value) {
        Node currentNode = root;
        while (!permission.isEmpty()) {
            int dotIndex = permission.indexOf('.');
            if (dotIndex == -1) {
                Node newNode = new Node(value);
                Node lastNode = currentNode.subNodes.put(permission, newNode);
                if ((lastNode != null) && (!lastNode.subNodes.isEmpty())) {
                    newNode.subNodes = lastNode.subNodes;
                }
                if (permission.equals("*")) {
                    currentNode.containsStarNode = true;
                }
                return;
            }
            String current = permission.substring(0, dotIndex);
            currentNode = currentNode.subNodes.computeIfAbsent(current, s -> new Node(false));

            permission = permission.substring(dotIndex + 1);
        }
    }
}
