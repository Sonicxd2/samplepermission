package ru.sonicxd2.samplepermission;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PermissionPlayerTest {
    @Test
    public void simpleTest() {
        PermissionPlayer player = new PermissionPlayer();
        Assert.assertFalse(player.isGranted("ad"));
        Assert.assertFalse(player.isGranted("*"));
        Assert.assertFalse(player.isGranted(""));
        player.grant("ad");
        player.grant("ad.hello");
        player.grant("hello.world");
        Assert.assertTrue(player.isGranted("ad"));
        Assert.assertTrue(player.isGranted("hello.world"));
        Assert.assertFalse(player.isGranted("hello"));
        Assert.assertTrue(player.isGranted("ad.hello"));
    }


    @org.junit.Test
    public void starTest() {
        PermissionPlayer player = new PermissionPlayer();
        player.grant("*");
        Assert.assertTrue("Star error.", player.hasAllPermissions());
        Assert.assertTrue(player.isGranted("hello.world"));
        player.revoke("*");
        Assert.assertFalse(player.hasAllPermissions());
    }
}